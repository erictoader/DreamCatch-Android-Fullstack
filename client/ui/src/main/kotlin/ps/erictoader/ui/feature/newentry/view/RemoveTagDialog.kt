package ps.erictoader.ui.feature.newentry.view

import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ps.erictoader.domain.feature.newentry.model.Tag
import ps.erictoader.ui.feature.common.view.GenericDialog
import ps.erictoader.ui.feature.common.view.StyledButton
import ps.erictoader.ui.feature.newentry.view.style.NewEntryScreenColors
import ps.erictoader.ui.feature.newentry.view.style.NewEntryScreenDimens
import ps.erictoader.ui.feature.newentry.view.style.NewEntryScreenTranslations

private val dimens = NewEntryScreenDimens
private val colors = NewEntryScreenColors
private val translations = NewEntryScreenTranslations

@Composable
fun RemoveTagDialog(
    tag: Tag,
    onDismissRequest: () -> Unit,
    onRemoveClicked: (Tag) -> Unit
) {
    val backgroundColor = remember { colors.ghost }

    GenericDialog(
        onDismissRequest = onDismissRequest
    ) {
        val prompt = remember {
            AnnotatedString.Builder().apply {
                append("Remove the tag ")
                pushStyle(SpanStyle(color = colors.stressTint))
                append(tag.name)
                pop()
                append("?")
            }.toAnnotatedString()
        }

        Text(
            text = prompt,
            fontWeight = FontWeight.Bold,
            fontSize = dimens.subtitle
        )

        StyledButton(
            width = 70.dp,
            height = 40.dp,
            imageVector = Icons.Default.Delete,
            contentDescription = "",
            onClick = {
                onRemoveClicked(tag)
                onDismissRequest()
            },
            isEnabled = true,
            backgroundColor = colors.stressTint,
            contentColor = backgroundColor
        )
    }
}

@Preview(device = Devices.PIXEL_4)
@Composable
private fun Preview_RemoveTagDialog() {
    RemoveTagDialog(
        Tag("Light dream", true), //false),
        {},
        {}
    )
}
