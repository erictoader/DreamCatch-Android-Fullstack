package ps.erictoader.ui

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ps.erictoader.ui.feature.common.util.DispatcherProvider
import ps.erictoader.ui.feature.common.util.DispatcherProviderImpl
import ps.erictoader.ui.feature.home.viewmodel.HomeViewModel
import ps.erictoader.ui.feature.newentry.viewmodel.NewEntryViewModel
import ps.erictoader.ui.feature.splash.viewmodel.SplashViewModel

val uiModule = module {
    single<DispatcherProvider> { DispatcherProviderImpl() }

    viewModel { SplashViewModel(get(), get()) }
    viewModel { HomeViewModel(get(), get(), get()) }
    viewModel { NewEntryViewModel(get(), get(), get(), get(), get()) }
}
