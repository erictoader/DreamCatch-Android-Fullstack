package ps.erictoader.domain.repo

interface UserRepo {

    suspend fun login()

    suspend fun register()

    suspend fun getCachedSession()
}
