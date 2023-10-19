package ps.erictoader.data.remote.abstraction

import ps.erictoader.data.model.SystemTagData

interface TagRemoteProxy {

    suspend fun getSystemTags(): List<SystemTagData>
}
