package ps.erictoader.data.model

import com.squareup.moshi.Json

data class SystemTagData(
    @Json(name = "name")
    val name: String
) : ModelMapper<String> {

    override fun mapToDomainModel(): String = name
}
