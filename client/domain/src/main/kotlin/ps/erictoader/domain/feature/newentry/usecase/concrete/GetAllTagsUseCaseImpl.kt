package ps.erictoader.domain.feature.newentry.usecase.concrete

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ps.erictoader.domain.feature.newentry.model.Tag
import ps.erictoader.domain.feature.newentry.usecase.abstraction.GetAllTagsUseCase
import ps.erictoader.domain.repo.TagRepo

internal class GetAllTagsUseCaseImpl (
    private val repo: TagRepo
) : GetAllTagsUseCase {

    override suspend fun invoke(): List<Tag> =
        withContext(Dispatchers.IO) {
            repo.getTags()
        }
}
