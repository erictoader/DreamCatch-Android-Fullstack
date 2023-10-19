package ps.erictoader.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ps.erictoader.ui.feature.home.view.HomeScreen
import ps.erictoader.ui.feature.newentry.view.NewEntryScreen
import ps.erictoader.ui.feature.splash.view.SplashScreen

@Composable
fun Navigator() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ScreenDestination.SplashScreen.route
    ) {
        composable(ScreenDestination.SplashScreen.route) {
            SplashScreen(navController = navController)
        }

        composable(ScreenDestination.HomeScreen.route) {
            HomeScreen(navController = navController)
        }

        composable(ScreenDestination.NewEntryScreen.route) {
            NewEntryScreen(navController = navController)
        }
    }
}

sealed class ScreenDestination(val route: String) {
    object SplashScreen: ScreenDestination("splash")
    object HomeScreen : ScreenDestination("home")
    object NewEntryScreen : ScreenDestination("new_entry")
}

fun NavController.navigate(destination: ScreenDestination) = navigate(destination.route)
