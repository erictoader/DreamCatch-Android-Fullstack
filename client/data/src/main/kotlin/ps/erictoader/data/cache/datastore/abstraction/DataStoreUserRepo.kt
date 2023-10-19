package ps.erictoader.data.cache.datastore.abstraction

interface DataStoreUserRepo {

    suspend fun getSession()

    suspend fun setSession()
}
