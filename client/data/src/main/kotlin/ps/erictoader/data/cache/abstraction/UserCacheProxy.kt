package ps.erictoader.data.cache.abstraction

interface UserCacheProxy {

    suspend fun getCachedSession()
}
