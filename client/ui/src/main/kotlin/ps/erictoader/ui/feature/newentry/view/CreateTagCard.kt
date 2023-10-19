package ps.erictoader.ui.feature.newentry.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ps.erictoader.ui.feature.newentry.view.style.NewEntryScreenColors
import ps.erictoader.ui.feature.newentry.view.style.NewEntryScreenDimens
import ps.erictoader.ui.feature.newentry.view.style.NewEntryScreenTranslations

private val dimens = NewEntryScreenDimens
private val colors = NewEntryScreenColors
private val translations = NewEntryScreenTranslations

@Composable
fun CreateTagCard(
    onClick: () -> Unit
) {
    val stroke = remember {
        Stroke(
            width = 8f,
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
        )
    }

    Row(
        modifier = Modifier
            .scale(0.95f)
            .height(50.dp)
            .clip(RoundedCornerShape(20.dp))
            .drawBehind {
                drawRoundRect(
                    color = colors.ghost,
                    style = stroke,
                    cornerRadius = CornerRadius(20.dp.toPx())
                )
            }
            .padding(12.dp)
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.weight(1f, fill = false),
            text = translations.CREATE_TAG,
            color = colors.ghost,
            fontSize = 20.sp,
            maxLines = 1
        )

        Icon(
            modifier = Modifier.size(30.dp),
            painter = rememberVectorPainter(image = Icons.Default.Add),
            contentDescription = null,
            tint = colors.ghost
        )
    }
}

@Preview(device = Devices.PIXEL_4)
@Composable
private fun Preview_CreateTagCard() {
    CreateTagCard(
        {}
    )
}
