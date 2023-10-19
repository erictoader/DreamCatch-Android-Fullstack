package ps.erictoader.domain.feature.newentry.usecase.abstraction

import ps.erictoader.domain.feature.newentry.model.Tag

interface GetAllTagsUseCase {

    suspend operator fun invoke() : List<Tag>
}