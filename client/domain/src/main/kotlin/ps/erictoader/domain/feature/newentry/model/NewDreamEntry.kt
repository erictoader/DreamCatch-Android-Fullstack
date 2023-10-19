package ps.erictoader.domain.feature.newentry.model

data class NewDreamEntry(
    val description: String,
    val tags: List<String>,
    val sleepDuration: Rating,
    val energyLevel: Rating,
    val stress: Rating
) {

    companion object {
        fun fromRawData(
            description: String,
            tags: List<String>,
            duration: Float,
            energy: Float,
            stress: Float
        ) = NewDreamEntry(
            description = description,
            tags = tags,
            sleepDuration = Rating(duration),
            energyLevel = Rating(energy),
            stress = Rating(stress)
        )
    }
}
