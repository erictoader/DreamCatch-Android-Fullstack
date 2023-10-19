package ps.erictoader.data

import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import ps.erictoader.data.cache.database.DreamCatchDbHelper
import ps.erictoader.data.cache.database.operations.abstraction.DreamDbOperations
import ps.erictoader.data.cache.database.operations.concrete.DreamDbOperationsImpl
import ps.erictoader.domain.feature.home.model.Dream
import ps.erictoader.domain.feature.newentry.model.NewDreamEntry
import ps.erictoader.domain.feature.newentry.model.Rating
import java.util.concurrent.ThreadLocalRandom

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class DreamCatchDbOperationsTest {

    private lateinit var dbHelper: SQLiteOpenHelper
    private lateinit var testContext: Context
    private lateinit var subject: DreamDbOperations

    @Before
    fun setUp() {
        testContext = InstrumentationRegistry.getInstrumentation().targetContext
        dbHelper = DreamCatchDbHelper(testContext)
        subject = DreamDbOperationsImpl(dbHelper)
    }

    @After
    fun tearDown() {
        dbHelper.close()
        testContext.deleteDatabase(dbHelper.databaseName)
    }

    @Test
    fun noOperationsExecuted_databaseIsEmpty() = runTest {
        //given: fresh database

        //when
        val movies = subject.getAllEntries()

        //then
        assert(movies.isEmpty())
    }

    @Test
    fun insertedOneElement_databaseContainsIt() = runTest {
        //given
        val insertedEntry = provideMockEntry()
        subject.addNewEntry(insertedEntry)

        //when
        val fetchedEntries = subject.getAllEntries()

        //then
        assert(fetchedEntries.size == 1)
        assertEqualsEntry(insertedEntry, fetchedEntries.first())
    }

    @Test
    fun insertedMultipleEntries_DatabaseContainsThem() = runTest {
        //given
        val insertedEntries = provideMockEntryList()
        insertedEntries.forEach { subject.addNewEntry(it) }

        //when
        val fetchedEntries = subject.getAllEntries()

        //then
        assert(fetchedEntries.size == insertedEntries.size)
        assertContainsAllEntries(insertedEntries, fetchedEntries)
    }

    @Test
    fun insertedEntriesWithTag_DatabaseContainsThem() = runTest {
        //given
        val tag = "Test Tag"
        val insertedEntries = provideMockEntryList()
        val insertedEntriesWithTag = provideMockEntryList(tag)
        (insertedEntries + insertedEntriesWithTag).forEach { subject.addNewEntry(it) }

        //when
        val fetchedEntries = subject.getAllEntriesByTag(tag)

        //then
        assert(fetchedEntries.size == insertedEntriesWithTag.size)
        assertContainsAllEntries(insertedEntriesWithTag, fetchedEntries)
    }

    @Test
    fun cleanedUpTag_DatabaseEntriesDoNotContainIt() = runTest {
        //given
        val tag = "Test Tag"
        val insertedEntries = provideMockEntryList()
        val insertedEntriesWithTag = provideMockEntryList(tag)
        val insertedEntriesTagRemoved = mutableListOf<NewDreamEntry>().apply {
            insertedEntriesWithTag.forEach { entry ->
                add(entry.copy(tags = entry.tags.filterNot { it == tag }))
            }
        }
        (insertedEntries + insertedEntriesWithTag).forEach { subject.addNewEntry(it) }

        //when
        subject.cleanupTagFromAllEntries(tag)
        val fetchedEntries = subject.getAllEntries()
        val fetchedEntriesByTag = subject.getAllEntriesByTag(tag)

        //then
        assert(fetchedEntriesByTag.isEmpty())
        assertContainsAllEntries(insertedEntries + insertedEntriesTagRemoved, fetchedEntries)
    }

    private fun provideMockEntryList(vararg mandatoryTags: String? = arrayOf(null)) =
        List(
            size = ThreadLocalRandom.current().nextInt(10),
            init = { provideMockEntry(*mandatoryTags) }
        )

    private fun provideMockEntry(vararg mandatoryTags: String? = arrayOf(null)) =
        NewDreamEntry(
            description = ThreadLocalRandom.current().nextInt().toString(),
            tags = MutableList(
                size = ThreadLocalRandom.current().nextInt(1, 10),
                init = { ThreadLocalRandom.current().nextInt().toString() }
            ).apply {
                mandatoryTags.forEach { tag ->
                    tag?.let {
                        add(it)
                    }
                }
            },
            sleepDuration = Rating(ThreadLocalRandom.current().nextFloat() * 5),
            energyLevel = Rating(ThreadLocalRandom.current().nextFloat() * 5),
            stress = Rating(ThreadLocalRandom.current().nextFloat() * 5)
        )

    private fun assertContainsAllEntries(
        inserted: List<NewDreamEntry>,
        fetched: List<Dream>
    ) {
        assert(inserted.size == fetched.size)
        fetched.forEach { fetchedEntry ->
            assert(
                inserted.contains(
                    NewDreamEntry(
                        description = fetchedEntry.description,
                        tags = fetchedEntry.tags,
                        sleepDuration = Rating(fetchedEntry.sleepDuration),
                        energyLevel = Rating(fetchedEntry.energyLevel),
                        stress = Rating(fetchedEntry.stress)
                    )
                )
            )
        }
    }

    private fun assertEqualsEntry(
        inserted: NewDreamEntry,
        fetched: Dream
    ) {
        assert(fetched.description == inserted.description)
        assert(fetched.sleepDuration == inserted.sleepDuration.value)
        assert(fetched.energyLevel == inserted.energyLevel.value)
        assert(fetched.stress == inserted.stress.value)
        assert(fetched.tags == inserted.tags)
    }
}
