package ps.erictoader.data.model

import com.squareup.moshi.Json
import ps.erictoader.domain.feature.home.model.DreamModule

data class DreamModuleData(
    @Json(name = "tag")
    val tag: String,
    @Json(name = "items")
    val items: List<DreamData>
) : ModelMapper<DreamModule> {

    override fun mapToDomainModel() =
        DreamModule(
            tag = tag,
            items = items.map { it.mapToDomainModel() }
        )
}
