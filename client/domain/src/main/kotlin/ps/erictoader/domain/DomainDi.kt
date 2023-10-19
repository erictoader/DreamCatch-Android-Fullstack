package ps.erictoader.domain

import org.koin.dsl.module
import ps.erictoader.domain.feature.home.usecase.abstraction.GetAllModulesUseCase
import ps.erictoader.domain.feature.home.usecase.abstraction.GetRecentModuleUseCase
import ps.erictoader.domain.feature.home.usecase.concrete.GetAllModulesUseCaseImpl
import ps.erictoader.domain.feature.home.usecase.concrete.GetRecentModuleUseCaseImpl
import ps.erictoader.domain.feature.newentry.usecase.abstraction.AddNewEntryUseCase
import ps.erictoader.domain.feature.newentry.usecase.abstraction.AddNewTagUseCase
import ps.erictoader.domain.feature.newentry.usecase.abstraction.GetAllTagsUseCase
import ps.erictoader.domain.feature.newentry.usecase.abstraction.RemoveTagUseCase
import ps.erictoader.domain.feature.newentry.usecase.concrete.AddNewEntryUseCaseImpl
import ps.erictoader.domain.feature.newentry.usecase.concrete.AddNewTagUseCaseImpl
import ps.erictoader.domain.feature.newentry.usecase.concrete.GetAllTagsUseCaseImpl
import ps.erictoader.domain.feature.newentry.usecase.concrete.RemoveTagUseCaseImpl
import ps.erictoader.domain.feature.splash.FetchAndCacheSystemTagsUseCase
import ps.erictoader.domain.feature.splash.FetchAndCacheSystemTagsUseCaseImpl

val domainModule = module {
    factory<FetchAndCacheSystemTagsUseCase> { FetchAndCacheSystemTagsUseCaseImpl(get()) }

    factory<GetRecentModuleUseCase> { GetRecentModuleUseCaseImpl(get()) }
    factory<GetAllModulesUseCase> { GetAllModulesUseCaseImpl(get()) }

    factory<AddNewEntryUseCase> { AddNewEntryUseCaseImpl(get()) }
    factory<AddNewTagUseCase> { AddNewTagUseCaseImpl(get()) }
    factory<GetAllTagsUseCase> { GetAllTagsUseCaseImpl(get()) }
    factory<RemoveTagUseCase> { RemoveTagUseCaseImpl(get(), get()) }
}
