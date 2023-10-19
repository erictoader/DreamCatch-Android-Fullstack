package ps.erictoader.domain.feature.newentry.usecase.concrete

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ps.erictoader.domain.repo.DreamRepo
import ps.erictoader.domain.feature.newentry.model.NewDreamEntry
import ps.erictoader.domain.feature.newentry.usecase.abstraction.AddNewEntryUseCase

internal class AddNewEntryUseCaseImpl(
    private val repo: DreamRepo
) : AddNewEntryUseCase {

    override suspend fun invoke(newEntry: NewDreamEntry) =
        withContext(Dispatchers.IO) {
            repo.addEntry(newEntry)
        }
}
