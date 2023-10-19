package ps.erictoader.ui.feature.splash.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.CoroutineExceptionHandler
import ps.erictoader.domain.feature.splash.FetchAndCacheSystemTagsUseCase
import ps.erictoader.ui.feature.common.util.DispatcherProvider
import ps.erictoader.ui.feature.common.viewmodel.BaseViewModel

class SplashViewModel(
    dispatcherProvider: DispatcherProvider,
    private val fetchAndCacheSystemTagsUseCase: FetchAndCacheSystemTagsUseCase
) : BaseViewModel(dispatcherProvider) {

    sealed class State {
        object Loading : State()
        object Success : State()
        object Error : State()
    }

    var state = mutableStateOf<State>(State.Loading)
        private set

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        Log.e(TAG, "Caught exception ${exception.message}")
        state.value = State.Error
    }

    fun fetchAndCacheSystemTags() =
        launchOnIO(exceptionHandler) {
            fetchAndCacheSystemTagsUseCase()
            state.value = State.Success
        }

    private companion object {
        private val TAG = SplashViewModel::class.java.simpleName
    }
}
