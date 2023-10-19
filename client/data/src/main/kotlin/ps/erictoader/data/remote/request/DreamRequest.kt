package ps.erictoader.data.remote.request

sealed class DreamRequest {

    data class Create(
        val userId: Int,
        val description: String,
        val tags: List<String>,
        val sleepDuration: Float,
        val energyLevel: Float,
        val stressLevel: Float
    ) : DreamRequest()

    data class Cleanup(
        val userId: Int,
        val tag: String
    ) : DreamRequest()
}
