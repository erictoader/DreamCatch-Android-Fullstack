package ps.erictoader.data.remote.api

import ps.erictoader.data.model.DreamData
import ps.erictoader.data.model.DreamModuleData
import ps.erictoader.data.model.Response
import ps.erictoader.data.remote.request.DreamRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface DreamApi {

    @GET("getByUserId")
    suspend fun getAll(
        @Query("userId") userId: Int
    ): Response<List<DreamData>>

    @GET("getByUserIdGroupedByTag")
    suspend fun getDreamModules(
        @Query("userId") userId: Int
    ): Response<List<DreamModuleData>>

    @POST("create")
    suspend fun create(
        @Body request: DreamRequest.Create
    ): Response<DreamData>

    @PUT("cleanupTag")
    suspend fun cleanupTag(
        @Body request: DreamRequest.Cleanup
    )
}
