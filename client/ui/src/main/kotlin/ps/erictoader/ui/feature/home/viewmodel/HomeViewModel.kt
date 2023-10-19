package ps.erictoader.ui.feature.home.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
import ps.erictoader.domain.feature.home.model.DreamModule
import ps.erictoader.domain.feature.home.usecase.abstraction.GetAllModulesUseCase
import ps.erictoader.domain.feature.home.usecase.abstraction.GetRecentModuleUseCase
import ps.erictoader.ui.feature.common.util.DispatcherProvider
import ps.erictoader.ui.feature.common.viewmodel.BaseViewModel

class HomeViewModel(
    dispatcherProvider: DispatcherProvider,
    private val getAllModulesUseCase: GetAllModulesUseCase,
    private val getRecentModuleUseCase: GetRecentModuleUseCase
) : BaseViewModel(dispatcherProvider) {

    sealed class State {
        object Loading : State()
        data class ModulesLoaded(val modules: List<DreamModule>) : State()
        object Error : State()
    }

    var state = mutableStateOf<State>(State.Loading)
        private set

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        Log.e(TAG, "Caught exception ${exception.message}")
        state.value = State.Error
    }

    fun loadModules() =
        launchOnIO(exceptionHandler) {
            val recentModule = async { getRecentModuleUseCase() }
            val taggedModules = async { getAllModulesUseCase() }

            state.value = State.ModulesLoaded(
                listOf(recentModule.await()) +
                        taggedModules.await()
            )
        }

    private companion object {
        private val TAG = HomeViewModel::class.java.simpleName
    }
}
