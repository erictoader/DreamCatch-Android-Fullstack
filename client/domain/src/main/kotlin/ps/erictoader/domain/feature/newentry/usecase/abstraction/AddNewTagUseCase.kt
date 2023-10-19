package ps.erictoader.domain.feature.newentry.usecase.abstraction

import ps.erictoader.domain.feature.newentry.model.Tag

interface AddNewTagUseCase {

    suspend operator fun invoke(tagName: String): Tag
}
