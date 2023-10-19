package ps.erictoader.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.*
import org.junit.Before
import org.junit.Rule
import ps.erictoader.data.cache.database.operations.abstraction.DreamDbOperations
import ps.erictoader.data.repo.DreamRepoImpl
import ps.erictoader.domain.repo.DreamRepo

@OptIn(ExperimentalCoroutinesApi::class)
class DreamRepoTest {
    @get:Rule
    val mockkRule = MockKRule(this)

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @MockK
    private lateinit var databaseOperations: DreamDbOperations

    private lateinit var subject: DreamRepo

    @Before
    fun setUp() {
        subject = DreamRepoImpl(databaseOperations)
    }
}
