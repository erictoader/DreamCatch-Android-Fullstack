package ps.erictoader.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.*
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import ps.erictoader.data.cache.datastore.concrete.DataStoreTagRepoImpl
import ps.erictoader.data.cache.datastore.util.DREAM_CATCH_SYSTEM_TAGS
import ps.erictoader.data.cache.datastore.abstraction.DataStoreTagRepo
import java.util.concurrent.ThreadLocalRandom

@OptIn(ExperimentalCoroutinesApi::class)
class DataStoreTagRepoTest {
    @get:Rule
    val mockkRule = MockKRule(this)

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @MockK
    private lateinit var testDataStore: DataStore<Preferences>

    private val tagsKey = stringSetPreferencesKey(DREAM_CATCH_SYSTEM_TAGS)
    private lateinit var preferences: MutablePreferences
    private lateinit var subject: DataStoreTagRepo

    @Before
    fun setUp() {
        preferences = mutablePreferencesOf(tagsKey to setOf())
        subject = DataStoreTagRepoImpl(testDataStore)
    }

    @Test
    fun noOperationsExecuted_dataStoreIsEmpty() = runTest {
        //given: fresh datastore

        //when
        val fetchedTags = subject.getSystemTags()

        //then
        assert(fetchedTags.isEmpty())
    }

    private fun provideMockTag(): String = ThreadLocalRandom.current().nextInt().toString()
}
