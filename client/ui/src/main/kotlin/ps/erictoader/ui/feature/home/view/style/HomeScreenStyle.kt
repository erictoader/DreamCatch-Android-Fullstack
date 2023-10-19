package ps.erictoader.ui.feature.home.view.style

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.*

object HomeScreenDimens {
    val verticalMargin = 32.dp
    val horizontalMargin = 8.dp
    val spacing = 20.dp
    val floatingButtonSize = 70.dp
    val floatingButtonIconSize = 50.dp
    val floatingButtonElevation = 20.dp
    val topMargin = 200.dp

    val title = 32.sp
    val header = 22.sp
}

object HomeScreenColors {
    val text = Color(0xFFD1CCDC)

    val yellowTint = Color(0xFFF8EE68)
    val cyanTint = Color(0xFF25A18E)
    val blushTint = Color(0xFFD55672)
    val ghostTint = Color(0xFFD1CCDC)
    val navyTint = Color(0xFF2B2D42)
}

object HomeScreenTranslations {
    private const val GREETING_AFTERNOON = "Good afternoon"
    private const val GREETING_EVENING = "Good evening"
    private const val GREETING_MORNING = "Good morning"
    private const val GREETING_NIGHT = "Good night"

    const val NO_ENTRIES = "No entries yet.\n Add your first using the\nfloating button below!"

    fun getGreetingByTime(): String {
        val cal: Calendar = Calendar.getInstance()
        cal.time = Date()

        return when (cal.get(Calendar.HOUR_OF_DAY)) {
            in 6..12 -> GREETING_MORNING
            in 12..18 -> GREETING_AFTERNOON
            in 18..21 -> GREETING_EVENING
            else -> GREETING_NIGHT
        }
    }

}
