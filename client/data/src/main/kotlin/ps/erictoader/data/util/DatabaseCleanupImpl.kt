package ps.erictoader.data.util

import ps.erictoader.data.cache.database.operations.abstraction.DreamDbOperations
import ps.erictoader.domain.util.DatabaseCleanup

class DatabaseCleanupImpl(
    private val dbOperations: DreamDbOperations
) : DatabaseCleanup {

    override fun invoke() = dbOperations.closeDatabase()
}
