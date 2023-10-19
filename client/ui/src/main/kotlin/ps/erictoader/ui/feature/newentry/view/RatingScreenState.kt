package ps.erictoader.ui.feature.newentry.view

enum class RatingScreenState {
    SLEEP_DURATION,
    ENERGY_LEVEL,
    STRESS_LEVEL;

    fun nextState(): RatingScreenState {
        return RatingScreenState.values()[this.ordinal + 1]
    }
}