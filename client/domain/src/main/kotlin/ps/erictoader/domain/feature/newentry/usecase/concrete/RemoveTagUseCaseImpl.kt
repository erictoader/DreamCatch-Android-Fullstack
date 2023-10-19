package ps.erictoader.domain.feature.newentry.usecase.concrete

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import ps.erictoader.domain.feature.newentry.model.Tag
import ps.erictoader.domain.feature.newentry.usecase.abstraction.RemoveTagUseCase
import ps.erictoader.domain.repo.DreamRepo
import ps.erictoader.domain.repo.TagRepo

internal class RemoveTagUseCaseImpl(
    private val tagRepo: TagRepo,
    private val dreamRepo: DreamRepo
) : RemoveTagUseCase {

    override suspend fun invoke(tag: Tag): Unit =
        withContext(Dispatchers.IO) {
            val cacheOperation = async { tagRepo.removeTag(tag.name) }
            val remoteOperation = async { dreamRepo.cleanupTagFromAllEntries(tag.name) }
            cacheOperation.await()
            remoteOperation.await()
        }
}
