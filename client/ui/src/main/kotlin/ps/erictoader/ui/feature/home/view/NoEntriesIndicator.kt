package ps.erictoader.ui.feature.home.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import ps.erictoader.ui.feature.home.view.style.HomeScreenColors
import ps.erictoader.ui.feature.home.view.style.HomeScreenDimens
import ps.erictoader.ui.feature.home.view.style.HomeScreenTranslations

private val dimens = HomeScreenDimens
private val colors = HomeScreenColors
private val translations = HomeScreenTranslations

@Composable
fun NoEntriesIndicator() {
    Spacer(modifier = Modifier.height(dimens.topMargin))
    Text(
        text = translations.NO_ENTRIES,
        color = colors.ghostTint.copy(.5f),
        textAlign = TextAlign.Center,
        fontSize = dimens.header
    )
}

@Preview(device = Devices.PIXEL_4)
@Composable
private fun Preview_NoEntriesIndicator() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    )
    NoEntriesIndicator()
}
