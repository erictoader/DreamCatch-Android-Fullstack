package ps.erictoader.domain.feature.newentry.usecase.concrete

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ps.erictoader.domain.feature.newentry.model.Tag
import ps.erictoader.domain.feature.newentry.usecase.abstraction.AddNewTagUseCase
import ps.erictoader.domain.repo.TagRepo

internal class AddNewTagUseCaseImpl(
    private val repo: TagRepo
) : AddNewTagUseCase {

    override suspend fun invoke(tagName: String): Tag =
        withContext(Dispatchers.IO) {
            repo.addUserTag(tagName)
        }
}
