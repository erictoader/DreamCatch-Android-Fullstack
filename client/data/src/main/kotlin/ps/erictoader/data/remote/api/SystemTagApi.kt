package ps.erictoader.data.remote.api

import ps.erictoader.data.model.Response
import ps.erictoader.data.model.SystemTagData
import retrofit2.http.GET

interface SystemTagApi {

    @GET("get")
    suspend fun getSystemTags(): Response<List<SystemTagData>>
}
