package ps.erictoader.ui.feature.newentry.view

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import ps.erictoader.ui.feature.common.view.StyledButton
import ps.erictoader.ui.feature.newentry.view.style.NewEntryScreenDimens
import ps.erictoader.ui.feature.newentry.view.style.NewEntryScreenTranslations

private val dimens = NewEntryScreenDimens
private val translations = NewEntryScreenTranslations

@Composable
fun NextButton(
    width: Dp,
    height: Dp,
    onClick: () -> Unit,
    isEnabled: Boolean
) {
    StyledButton(
        width = width,
        height = height,
        imageVector = Icons.Default.ArrowForward,
        contentDescription = translations.NEXT,
        onClick = onClick,
        isEnabled = isEnabled
    )
}

@Preview(device = Devices.PIXEL_4)
@Composable
private fun Preview_NextButton() {
    NextButton(dimens.buttonWidth, dimens.buttonHeight, {}, true)
}