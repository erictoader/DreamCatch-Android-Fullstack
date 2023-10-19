package ps.erictoader.ui.feature.newentry.view

import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ps.erictoader.ui.feature.common.view.GenericDialog
import ps.erictoader.ui.feature.common.view.StyledButton
import ps.erictoader.ui.feature.newentry.view.style.NewEntryScreenColors
import ps.erictoader.ui.feature.newentry.view.style.NewEntryScreenDimens
import ps.erictoader.ui.feature.newentry.view.style.NewEntryScreenTranslations

private val dimens = NewEntryScreenDimens
private val colors = NewEntryScreenColors
private val translations = NewEntryScreenTranslations

@Composable
fun AddTagDialog(
    onAddClicked: (String) -> Unit,
    onDismissRequest: () -> Unit
) {
    val backgroundColor = remember { colors.ghost }
    val contentColor = remember { Color.Black.copy(0.75f) }
    val focusRequester = remember { FocusRequester() }

    GenericDialog(
        onDismissRequest = onDismissRequest
    ) {
        var tagName by remember { mutableStateOf("") }

        Text(
            text = "Create a tag",
            color = contentColor,
            fontWeight = FontWeight.Bold,
            fontSize = dimens.subtitle
        )

        TextField(
            modifier = Modifier.focusRequester(focusRequester),
            value = tagName,
            onValueChange = { tagName = it },
            maxLines = 1,
            colors = TextFieldDefaults.textFieldColors(
                textColor = contentColor
            ),
            placeholder = {
                Text(
                    text = "Tag name...",
                    color = contentColor.copy(0.25f)
                )
            }
        )

        StyledButton(
            width = 70.dp,
            height = 40.dp,
            imageVector = Icons.Default.Done,
            contentDescription = "",
            onClick = {
                onAddClicked(tagName)
                onDismissRequest()
            },
            isEnabled = tagName.isNotBlank(),
            backgroundColor = colors.energyTint,
            contentColor = backgroundColor
        )
    }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}

@Preview(device = Devices.PIXEL_4)
@Composable
private fun Preview_AddTagDialog() {
    AddTagDialog(
        {},
        {}
    )
}
