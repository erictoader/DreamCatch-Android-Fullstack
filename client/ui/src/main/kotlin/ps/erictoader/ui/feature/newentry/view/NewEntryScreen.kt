package ps.erictoader.ui.feature.newentry.view

import androidx.compose.runtime.*
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ps.erictoader.ui.ScreenDestination
import ps.erictoader.ui.feature.common.view.PopupScreen
import ps.erictoader.ui.navigate

@Composable
fun NewEntryScreen(
    navController: NavController = rememberNavController()
) {
    val scope = rememberCoroutineScope()
    PopupScreen {
        var description by remember { mutableStateOf("") }
        var tags by remember { mutableStateOf(listOf<String>()) }
        var sleepDuration by remember { mutableStateOf(Float.NaN) }
        var energyLevel by remember { mutableStateOf(Float.NaN) }
        var stressLevel by remember { mutableStateOf(Float.NaN) }
        var screenState by remember { mutableStateOf(NewEntryScreenState.MAIN_SCREEN) }

        when (screenState) {
            NewEntryScreenState.MAIN_SCREEN -> {
                NewEntryMainScreen(
                    onNext = { desc, tagList ->
                        description = desc
                        tags = tagList
                        screenState = screenState.nextState()
                    }
                )
            }
            NewEntryScreenState.RATING_SCREEN -> {
                NewEntryRatingScreen(
                    onNext = { duration, energy, stress ->
                        sleepDuration = duration
                        energyLevel = energy
                        stressLevel = stress
                        screenState = screenState.nextState()
                    }
                )
            }
            NewEntryScreenState.SUBMIT_SCREEN -> {
                NewEntrySubmitScreen(
                    description = description,
                    tags = tags,
                    sleepDuration = sleepDuration,
                    energyLevel =  energyLevel,
                    stressLevel =  stressLevel,
                    onSubmitted = {
                        scope.launch {
                            withContext(Dispatchers.IO) {
                                delay(SUBMITTED_DISMISS_DELAY_MS)
                            }
                            withContext(Dispatchers.Main) {
                                navController.navigate(ScreenDestination.HomeScreen)
                            }
                        }
                    }
                )
            }
        }
    }
}

private const val SUBMITTED_DISMISS_DELAY_MS = 3000L
