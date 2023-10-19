package ps.erictoader.ui.feature.splash.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import org.koin.androidx.compose.koinViewModel
import ps.erictoader.ui.R
import ps.erictoader.ui.ScreenDestination
import ps.erictoader.ui.feature.splash.viewmodel.SplashViewModel
import ps.erictoader.ui.navigate

@Composable
fun SplashScreen(
    viewModel: SplashViewModel = koinViewModel(),
    navController: NavController = rememberNavController()
) {
    var isAnimationFinished by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2B2D42)),
        contentAlignment = Alignment.Center
    ) {
        val composition by rememberLottieComposition(
            spec = LottieCompositionSpec.RawRes(R.raw.splash_animation)
        )
        val progress by animateLottieCompositionAsState(composition)

        LottieAnimation(
            composition = composition,
            iterations = 1
        )

        if (progress == 1f) isAnimationFinished = true
    }

    LaunchedEffect(Unit) {
        viewModel.fetchAndCacheSystemTags()
    }

    LaunchedEffect(isAnimationFinished, viewModel.state.value) {
        if (isAnimationFinished) {
            when (viewModel.state.value) {
                is SplashViewModel.State.Loading -> Unit
                is SplashViewModel.State.Success -> {
                    navController.navigate(ScreenDestination.HomeScreen)
                }
                is SplashViewModel.State.Error -> TODO()
            }
        }
    }
}

@Preview(device = Devices.PIXEL_4)
@Composable
private fun Preview_SplashScreen() {
    SplashScreen()
}
