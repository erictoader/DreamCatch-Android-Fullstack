package ps.erictoader.ui.feature.common.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import ps.erictoader.ui.feature.common.style.FullScreenColors

private val colors = FullScreenColors

@Composable
fun FullScreen(
    modifier: Modifier = Modifier,
    content: @Composable (BoxScope.() -> Unit)
) {
//    val scrollState = rememberScrollState()
    Column {
        Box(
            modifier = modifier
                .fillMaxSize()
//                .verticalScroll(scrollState)
//                .weight(1f, false)
                .background(colors.background),
            content = content
        )
    }
}

@Preview(device = Devices.PIXEL_4)
@Composable
private fun Preview_FullScreen() {
    FullScreen {

    }
}