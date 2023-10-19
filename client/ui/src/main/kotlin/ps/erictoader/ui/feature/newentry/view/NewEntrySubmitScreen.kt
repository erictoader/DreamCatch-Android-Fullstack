package ps.erictoader.ui.feature.newentry.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import org.koin.androidx.compose.koinViewModel
import ps.erictoader.ui.R
import ps.erictoader.ui.feature.common.view.LoadingIndicator
import ps.erictoader.ui.feature.common.view.PopupScreen
import ps.erictoader.ui.feature.newentry.view.style.NewEntryScreenColors
import ps.erictoader.ui.feature.newentry.view.style.NewEntryScreenDimens
import ps.erictoader.ui.feature.newentry.view.style.NewEntryScreenTranslations
import ps.erictoader.ui.feature.newentry.viewmodel.NewEntryViewModel

private val dimens = NewEntryScreenDimens
private val colors = NewEntryScreenColors
private val translations = NewEntryScreenTranslations

@Composable
fun NewEntrySubmitScreen(
    description: String,
    tags: List<String>,
    sleepDuration: Float,
    energyLevel: Float,
    stressLevel: Float,
    viewModel: NewEntryViewModel = koinViewModel(),
    onSubmitted: () -> Unit
) {
    var submitted by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(
                vertical = dimens.verticalMargin,
                horizontal = dimens.horizontalMargin
            )
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(dimens.spacing)
    ) {
        Spacer(modifier = Modifier.size(dimens.topBottomSpacing))

        Text(
            text = if (!submitted) translations.SUBMITTING else translations.ALL_SET,
            color = colors.ghost,
            fontSize = dimens.title,
            fontWeight = FontWeight.Bold
        )
        val composition by rememberLottieComposition(
            spec = LottieCompositionSpec.RawRes(R.raw.done)
        )

        if (submitted) {
            LottieAnimation(
                modifier = Modifier.size(dimens.progressIndicatorSize),
                composition = composition,
                iterations = 1
            )
        } else LoadingIndicator()

        Spacer(modifier = Modifier.weight(1f))

        when(viewModel.state.value) {
            is NewEntryViewModel.State.EntryAdded -> {
                submitted = true
                onSubmitted()
            }
            else -> Unit
        }
    }

    LaunchedEffect(Unit) {
        viewModel.addNewEntry(description, tags, sleepDuration, energyLevel, stressLevel)
    }
}

@Preview(device = Devices.PIXEL_4)
@Composable
private fun Preview_NewEntrySubmitScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    )
    PopupScreen {
        NewEntrySubmitScreen(
            onSubmitted = { },
            description = "",
            tags = listOf(),
            sleepDuration = 0f,
            energyLevel = 0f,
            stressLevel = 0f
        )
    }
}

