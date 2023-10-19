package ps.erictoader.data.remote.concrete

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ps.erictoader.data.model.SystemTagData
import ps.erictoader.data.remote.abstraction.TagRemoteProxy
import ps.erictoader.data.remote.api.SystemTagApi

class TagRemoteProxyImpl(
    private val api: SystemTagApi
) : TagRemoteProxy {

    override suspend fun getSystemTags(): List<SystemTagData> =
        withContext(Dispatchers.IO) {
            val response = api.getSystemTags()
            response.data
        }
}
