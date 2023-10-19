package ps.erictoader.domain.feature.newentry.usecase.abstraction

import ps.erictoader.domain.feature.newentry.model.Tag

interface RemoveTagUseCase {

    suspend operator fun invoke(tag: Tag)
}
