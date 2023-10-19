package ps.erictoader.domain.feature.home.usecase.abstraction

import ps.erictoader.domain.feature.home.model.DreamModule

interface GetAllModulesUseCase {

    suspend operator fun invoke(): List<DreamModule>
}
