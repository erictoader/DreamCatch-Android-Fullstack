package ps.erictoader.ui.feature.newentry.view.style

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.max
import kotlin.math.pow

object NewEntryScreenDimens {
    val verticalMargin = 16.dp
    val horizontalMargin = 32.dp
    val spacing = 60.dp
    val textFieldSpacing = 10.dp
    val topBottomSpacing = 30.dp

    val buttonWidth = 100.dp
    val buttonHeight = 50.dp

    val ratingItem = 50.dp

    val progressIndicatorSize = 200.dp

    val title = 32.sp
    val subtitle = 24.sp

    const val TEXT_FIELD_MIN_LINES = 8
    const val TEXT_FIELD_MAX_LINES = 12
}

object NewEntryScreenColors {
    val titleText = Color(0xFFF8EE68)
    val ghost = Color(0xFFD1CCDC)

    val sleepTint = Color(0xFFF8EE68)
    val energyTint = Color(0xFF25A18E)
    val stressTint = Color(0xFFD55672)

    private val tagColors = listOf(sleepTint, energyTint, stressTint)
    fun getRandomColor() = tagColors.random()

    fun Color.highContrastColor() =
        Color(
            red = 1f - this.luminance().toDouble().pow(2.0).toFloat(),
            green = 1f - this.luminance().toDouble().pow(2.0).toFloat(),
            blue = 1f - this.luminance().toDouble().pow(2.0).toFloat(),
            alpha = 1f
        )

    fun Color.darkerShade() =
        Color(
            red = max(0f, this.red - 0.3f),
            green = max(0f, this.green - 0.3f),
            blue = max(0f, this.blue - 0.3f),
            alpha = 1f
        )
}

object NewEntryScreenTranslations {
    const val GOOD_MORNING = "Good morning!"
    const val HOW_WAS_SLEEP = "How was your sleep?"
    const val WRITE_SHORT_DESCRIPTION = "Make a few notes about it..."

    const val RATE_SLEEP_DURATION = "Rate your sleep duration"
    const val RATE_ENERGY_LEVEL = "Rate your current energy level"
    const val RATE_STRESS_LEVEL = "Rate your current stress level"
    const val SUBMITTING = "Submitting..."
    const val ALL_SET = "You're all set!"

    const val NEXT = "NEXT"

    const val CREATE_TAG = "Create Tag"
}
