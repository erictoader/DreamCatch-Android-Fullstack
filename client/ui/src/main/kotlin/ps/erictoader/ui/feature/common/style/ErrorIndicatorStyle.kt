package ps.erictoader.ui.feature.common.style

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

object ErrorIndicatorDimens {
    val topMargin = 40.dp
    val spacing = 10.dp
    val icon = 60.dp

    val text = 18.sp
}

object ErrorIndicatorColors {
    val tint = Color(0xFFF8EE68)
}

object ErrorIndicatorTranslations {
    const val UNEXPECTED_ERROR = "An unexpected error occurred.\nTry again later."
}