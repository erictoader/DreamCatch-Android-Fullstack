package ps.erictoader.ui.feature.newentry.view

enum class NewEntryScreenState {
    MAIN_SCREEN,
    RATING_SCREEN,
    SUBMIT_SCREEN;

    fun nextState(): NewEntryScreenState {
        return NewEntryScreenState.values()[this.ordinal + 1]
    }
}
