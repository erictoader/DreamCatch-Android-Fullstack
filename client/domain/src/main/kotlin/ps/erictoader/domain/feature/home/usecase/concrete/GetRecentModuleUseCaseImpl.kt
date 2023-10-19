package ps.erictoader.domain.feature.home.usecase.concrete

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ps.erictoader.domain.repo.DreamRepo
import ps.erictoader.domain.feature.home.model.DreamModule
import ps.erictoader.domain.feature.home.usecase.abstraction.GetRecentModuleUseCase

internal class GetRecentModuleUseCaseImpl(
    private val repo: DreamRepo
) : GetRecentModuleUseCase {

    override suspend fun invoke(): DreamModule =
        withContext(Dispatchers.IO) {
            val entries = repo.getRecentEntries()
            DreamModule(RECENT_SLEEP_HEADER, entries)
        }

    private companion object {
        private const val RECENT_SLEEP_HEADER = "Recent sleep"
    }
}
