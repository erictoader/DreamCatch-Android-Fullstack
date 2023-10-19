package ps.erictoader.ui.feature.common.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import ps.erictoader.ui.feature.common.style.PopupScreenColors
import ps.erictoader.ui.feature.common.style.PopupScreenDimens

private val dimens = PopupScreenDimens
private val colors = PopupScreenColors

@Composable
fun PopupScreen(content: @Composable (BoxScope.() -> Unit)) {
    // TODO: Remove this black background when screen animations are integrated
    Box(modifier = Modifier
        .background(Color.Black)
        .fillMaxSize()
    )
    Column {
        Spacer(modifier = Modifier.height(dimens.topEmptySpace))
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(
                    RoundedCornerShape(
                        topStart = dimens.roundedCorner,
                        topEnd = dimens.roundedCorner
                    )
                )
                .background(colors.background),
            content = content
        )
    }
}

@Preview(device = Devices.PIXEL_4)
@Composable
private fun Preview_PopupScreen() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Red)) {

    }
    PopupScreen {}
}
