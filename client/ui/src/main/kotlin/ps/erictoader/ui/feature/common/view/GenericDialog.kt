package ps.erictoader.ui.feature.common.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import ps.erictoader.ui.feature.newentry.view.style.NewEntryScreenColors
import ps.erictoader.ui.feature.newentry.view.style.NewEntryScreenColors.darkerShade
import ps.erictoader.ui.feature.newentry.view.style.NewEntryScreenDimens

private val dimens = NewEntryScreenDimens
private val colors = NewEntryScreenColors

@Composable
fun GenericDialog(
    onDismissRequest: () -> Unit,
    content: @Composable (ColumnScope.() -> Unit)
) {
    val backgroundColor = remember { colors.ghost }
    val borderColor = remember { colors.ghost.darkerShade() }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Dialog(
            onDismissRequest = onDismissRequest,
            // TODO: This is a bug in Compose. Normally it shouldn't be here
            //  Resizing doesn't work without it. Remove when possible
            properties = DialogProperties(usePlatformDefaultWidth = false)
        ) {
            Surface(
                modifier = Modifier
                    .padding(horizontal = 50.dp)
                    .fillMaxWidth(),
                elevation = 5.dp,
                shape = RoundedCornerShape(20.dp),
                color = backgroundColor,
                border = BorderStroke(2.dp, borderColor)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(18.dp),
                    content = content
                )
            }
        }
    }
}
