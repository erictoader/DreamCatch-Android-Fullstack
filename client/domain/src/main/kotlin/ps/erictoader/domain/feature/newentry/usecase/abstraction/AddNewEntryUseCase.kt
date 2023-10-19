package ps.erictoader.domain.feature.newentry.usecase.abstraction

import ps.erictoader.domain.feature.newentry.model.NewDreamEntry

interface AddNewEntryUseCase {

    suspend operator fun invoke(newEntry: NewDreamEntry)
}
