package ps.erictoader.domain.feature.home.usecase.abstraction

import ps.erictoader.domain.feature.home.model.DreamModule

interface GetRecentModuleUseCase {

    suspend operator fun invoke(): DreamModule
}
