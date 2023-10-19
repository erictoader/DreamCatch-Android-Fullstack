package ps.erictoader.data.repo

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ps.erictoader.data.cache.abstraction.UserCacheProxy
import ps.erictoader.data.remote.abstraction.UserRemoteProxy
import ps.erictoader.domain.repo.UserRepo

class UserRepoImpl(
    private val remoteProxy: UserRemoteProxy,
    private val cacheProxy: UserCacheProxy
) : UserRepo {

    override suspend fun login() : Unit =
        withContext(Dispatchers.IO) {
            TODO("Not yet implemented")
        }

    override suspend fun register() : Unit =
        withContext(Dispatchers.IO) {
            TODO("Not yet implemented")
        }

    override suspend fun getCachedSession() : Unit =
        withContext(Dispatchers.IO) {
            TODO("Not yet implemented")
        }
}
