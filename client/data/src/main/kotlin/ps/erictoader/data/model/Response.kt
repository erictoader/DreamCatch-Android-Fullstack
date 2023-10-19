package ps.erictoader.data.model

data class Response<D>(
    val code: Int,
    val message: String,
    val data: D
) {
    val isSuccess: Boolean
        get() = code == SUCCESS_CODE

    private companion object {
        private const val SUCCESS_CODE = 200
    }
}
