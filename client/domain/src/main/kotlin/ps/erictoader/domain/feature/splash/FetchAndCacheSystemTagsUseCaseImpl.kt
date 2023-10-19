package ps.erictoader.domain.feature.splash

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ps.erictoader.domain.repo.TagRepo

class FetchAndCacheSystemTagsUseCaseImpl(
    private val repo: TagRepo
) : FetchAndCacheSystemTagsUseCase {

    override suspend fun invoke() =
        withContext(Dispatchers.IO) {
            repo.fetchAndCacheSystemTags()
        }
}
