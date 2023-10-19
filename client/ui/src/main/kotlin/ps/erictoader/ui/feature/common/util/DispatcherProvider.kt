package ps.erictoader.ui.feature.common.util

import kotlinx.coroutines.CoroutineDispatcher

interface DispatcherProvider {

    fun getMain(): CoroutineDispatcher

    fun getIO(): CoroutineDispatcher

    fun getDefault(): CoroutineDispatcher

    fun getUnconfined(): CoroutineDispatcher
}