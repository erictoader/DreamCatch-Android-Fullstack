package ps.erictoader.ui.feature.newentry.view

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ps.erictoader.domain.feature.newentry.model.Tag
import ps.erictoader.ui.feature.newentry.view.style.NewEntryScreenColors
import ps.erictoader.ui.feature.newentry.view.style.NewEntryScreenColors.darkerShade
import ps.erictoader.ui.feature.newentry.view.style.NewEntryScreenColors.highContrastColor
import ps.erictoader.ui.feature.newentry.view.style.NewEntryScreenDimens

private val dimens = NewEntryScreenDimens
private val colors = NewEntryScreenColors

@Composable
fun TagCard(
    tag: Tag,
    focused: Boolean,
    onClick: () -> Unit,
    onDelete: () -> Unit = { }
) {
    val backgroundColor = remember { colors.getRandomColor() }
    val contentColor = remember { backgroundColor.highContrastColor() }
    val scaleFactor by animateFloatAsState(targetValue = if (focused) 1f else .95f)

    Row(
        modifier = Modifier
            .scale(scaleFactor)
            .height(50.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(backgroundColor)
            .border(
                width = 2.dp,
                color = if (focused) Color.White else backgroundColor.darkerShade(),
                shape = RoundedCornerShape(20.dp)
            )
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .weight(1f)
                .clickable { onClick() },
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = tag.name,
                color = contentColor,
                fontSize = 20.sp,
                maxLines = 1
            )
        }

        if (tag.isEditable) {
            Icon(
                modifier = Modifier
                    .size(30.dp)
                    .clickable { onDelete() },
                painter = rememberVectorPainter(image = Icons.Default.Close),
                contentDescription = null,
                tint = contentColor
            )
        }
    }
}

@Preview(device = Devices.PIXEL_4)
@Composable
private fun Preview_TagCard() {
    TagCard(
        tag = Tag(
            name = "Light sleep",
            isEditable = true,
            //isDefault = false
        ),
        focused = false,
        onClick = { },
        onDelete = { }
    )
}
