package ps.erictoader.ui.feature.common.view

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ps.erictoader.ui.feature.common.style.StyledButtonColors
import ps.erictoader.ui.feature.common.style.StyledButtonDimens

private val dimens = StyledButtonDimens
private val colors = StyledButtonColors

@Composable
fun StyledButton(
    width: Dp,
    height: Dp,
    imageVector: ImageVector,
    contentDescription: String,
    onClick: () -> Unit,
    isEnabled: Boolean,
    backgroundColor: Color = colors.buttonBackground,
    contentColor: Color = colors.buttonContent
) {
    val actualWidth by animateDpAsState(targetValue = if (isEnabled) width else 0.dp)
    val actualHeight by animateDpAsState(targetValue = if (isEnabled) height else 0.dp)

    Button(
        modifier = Modifier
            .width(actualWidth)
            .height(actualHeight),
        onClick = onClick,
        enabled = isEnabled,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
            contentColor = contentColor
        ),
        shape = RoundedCornerShape(dimens.buttonRoundedPercent)
    ) {
        Icon(
            modifier = Modifier.fillMaxSize(),
            painter = rememberVectorPainter(image = imageVector),
            contentDescription = contentDescription
        )
    }
}