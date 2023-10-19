package ps.erictoader.ui.feature.common.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class DispatcherProviderImpl : DispatcherProvider {

    override fun getMain(): CoroutineDispatcher = Dispatchers.Main

    override fun getIO(): CoroutineDispatcher = Dispatchers.IO

    override fun getDefault(): CoroutineDispatcher = Dispatchers.Default

    override fun getUnconfined(): CoroutineDispatcher = Dispatchers.Unconfined
}