package ps.erictoader.ui.feature.common.view

import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import ps.erictoader.ui.feature.common.style.LoadingIndicatorColors
import ps.erictoader.ui.feature.common.style.LoadingIndicatorDimens

private val dimens = LoadingIndicatorDimens
private val colors = LoadingIndicatorColors

@Composable
fun LoadingIndicator() {
    CircularProgressIndicator(
        modifier = Modifier.size(dimens.size),
        color = colors.color
    )
}

@Preview(device = Devices.PIXEL_4)
@Composable
private fun Preview_LoadingIndicator() {
    LoadingIndicator()
}