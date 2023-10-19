package ps.erictoader.domain.feature.home.usecase.concrete

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ps.erictoader.domain.feature.home.model.DreamModule
import ps.erictoader.domain.feature.home.usecase.abstraction.GetAllModulesUseCase
import ps.erictoader.domain.repo.DreamRepo

internal class GetAllModulesUseCaseImpl(
    private val dreamRepo: DreamRepo,
) : GetAllModulesUseCase {

    override suspend fun invoke(): List<DreamModule> =
        withContext(Dispatchers.IO) {
            dreamRepo.getDreamModules()
        }
}
