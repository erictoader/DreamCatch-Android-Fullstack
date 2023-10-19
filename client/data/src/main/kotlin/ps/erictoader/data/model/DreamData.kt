package ps.erictoader.data.model

import com.squareup.moshi.Json
import ps.erictoader.domain.feature.home.model.Dream

data class DreamData(
    @Json(name = "description")
    val description: String,
    @Json(name = "sleepDuration")
    val sleepDuration: Float,
    @Json(name = "energyLevel")
    val energyLevel: Float,
    @Json(name = "stressLevel")
    val stressLevel: Float,
    @Json(name = "sleepScore")
    val sleepScore: Float,
    @Json(name = "entryDate")
    val entryDate: Long
) : ModelMapper<Dream> {

    override fun mapToDomainModel() =
        Dream(
            description = description,
            sleepDuration = sleepDuration,
            energyLevel = energyLevel,
            stress = stressLevel,
            sleepScore = sleepScore,
            entryDate = entryDate
        )
}
