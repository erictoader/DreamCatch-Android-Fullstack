package ps.erictoader.data.cache.concrete

import ps.erictoader.data.cache.abstraction.DreamCacheProxy
import ps.erictoader.data.cache.database.operations.abstraction.DreamDbOperations

class DreamCacheProxyImpl(
    private val dbOperations: DreamDbOperations
) : DreamCacheProxy {
}
