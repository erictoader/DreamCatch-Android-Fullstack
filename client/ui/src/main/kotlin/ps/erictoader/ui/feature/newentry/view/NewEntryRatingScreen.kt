package ps.erictoader.ui.feature.newentry.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Nightlight
import androidx.compose.material.icons.filled.Power
import androidx.compose.material.icons.outlined.ErrorOutline
import androidx.compose.material.icons.outlined.Nightlight
import androidx.compose.material.icons.outlined.Power
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.smarttoolfactory.ratingbar.RatingBar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ps.erictoader.ui.feature.common.view.PopupScreen
import ps.erictoader.ui.feature.newentry.view.style.NewEntryScreenColors
import ps.erictoader.ui.feature.newentry.view.style.NewEntryScreenDimens
import ps.erictoader.ui.feature.newentry.view.style.NewEntryScreenTranslations

private val dimens = NewEntryScreenDimens
private val colors = NewEntryScreenColors
private val translations = NewEntryScreenTranslations

private const val ANIMATION_VISIBILITY_TIME = 500

@Composable
fun NewEntryRatingScreen(
    onNext : (Float, Float, Float) -> Unit
) {
    PopupScreen {
        var duration by remember { mutableStateOf(Float.NaN) }
        var energy by remember { mutableStateOf(Float.NaN) }
        var stress by remember { mutableStateOf(Float.NaN) }
        var screenState by remember { mutableStateOf(RatingScreenState.SLEEP_DURATION) }

        when (screenState) {
            RatingScreenState.SLEEP_DURATION -> {
                RatingScreen(
                    title = translations.RATE_SLEEP_DURATION,
                    ratingVectorEmpty = Icons.Outlined.Nightlight,
                    ratingVectorFilled = Icons.Filled.Nightlight,
                    screenTint = colors.sleepTint
                ) { rating ->
                    duration = rating
                    screenState = screenState.nextState()
                }
            }
            RatingScreenState.ENERGY_LEVEL -> {
                RatingScreen(
                    title = translations.RATE_ENERGY_LEVEL,
                    ratingVectorEmpty = Icons.Outlined.Power,
                    ratingVectorFilled = Icons.Filled.Power,
                    screenTint = colors.energyTint
                ) { rating ->
                    energy = rating
                    screenState = screenState.nextState()
                }
            }
            RatingScreenState.STRESS_LEVEL -> {
                RatingScreen(
                    title = translations.RATE_STRESS_LEVEL,
                    ratingVectorEmpty = Icons.Outlined.ErrorOutline,
                    ratingVectorFilled = Icons.Filled.Error,
                    screenTint = colors.stressTint
                ) { rating ->
                    stress = rating
                    onNext(duration, energy, stress)
                }
            }
        }
    }
}

@Preview(device = Devices.PIXEL_4)
@Composable
private fun Preview_NewEntryRatingScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    )
    NewEntryRatingScreen(
        onNext = {_, _, _ ->}
    )
}

@Composable
private fun RatingScreen(
    title: String,
    ratingVectorEmpty: ImageVector,
    ratingVectorFilled: ImageVector,
    screenTint: Color,
    onSubmit: (Float) -> Unit
) {
    var isVisible by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn(animationSpec = tween(ANIMATION_VISIBILITY_TIME)),
        exit = fadeOut(animationSpec = tween(ANIMATION_VISIBILITY_TIME))
    ) {
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
            var rating by remember { mutableStateOf(Float.NaN) }

            Spacer(modifier = Modifier.size(dimens.topBottomSpacing))

            Text(
                text = title,
                color = screenTint,
                fontSize = dimens.title,
                fontWeight = FontWeight.Bold
            )

            RatingBar(
                rating = 0f,
                imageVectorEmpty = ratingVectorEmpty,
                imageVectorFFilled = ratingVectorFilled,
                tintEmpty = screenTint,
                tintFilled = screenTint,
                itemSize = dimens.ratingItem,
                onRatingChange = { rating = it }
            )

            Spacer(modifier = Modifier.weight(1f))

            NextButton(
                width = dimens.buttonWidth,
                height = dimens.buttonHeight,
                onClick = {
                    scope.launch {
                        withContext(Dispatchers.IO) {
                            isVisible = false
                            delay(ANIMATION_VISIBILITY_TIME.toLong())
                        }
                        withContext(Dispatchers.Main) {
                            onSubmit(rating)
                        }
                    }
                },
                isEnabled = !rating.isNaN()
            )

            Spacer(modifier = Modifier.size(dimens.topBottomSpacing))
        }
    }

    LaunchedEffect(Unit) {
        isVisible = true
    }
}

@Preview(device = Devices.PIXEL_4)
@Composable
private fun Preview_RatingScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    )
    PopupScreen {
        RatingScreen(
            title = translations.RATE_SLEEP_DURATION,
            ratingVectorEmpty = Icons.Outlined.Nightlight,
            ratingVectorFilled = Icons.Filled.Nightlight,
            screenTint = colors.sleepTint
        ) {

        }
    }
}