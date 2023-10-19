package ps.erictoader.ui.feature.common.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import ps.erictoader.ui.feature.common.style.ErrorIndicatorColors
import ps.erictoader.ui.feature.common.style.ErrorIndicatorDimens
import ps.erictoader.ui.feature.common.style.ErrorIndicatorTranslations

private val dimens = ErrorIndicatorDimens
private val colors = ErrorIndicatorColors
private val translations = ErrorIndicatorTranslations

@Composable
fun ErrorIndicator() {
    Column(
        modifier = Modifier.padding(top = dimens.topMargin),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(dimens.spacing)
    ) {
        Icon(
            modifier = Modifier.size(dimens.icon),
            painter = rememberVectorPainter(image = Icons.Default.Error),
            contentDescription = translations.UNEXPECTED_ERROR,
            tint = colors.tint
        )

        Text(
            text = translations.UNEXPECTED_ERROR,
            color = colors.tint,
            fontSize = dimens.text,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
    }

}

@Preview(device = Devices.PIXEL_4)
@Composable
private fun Preview_ErrorIndicator() {
    ErrorIndicator()
}