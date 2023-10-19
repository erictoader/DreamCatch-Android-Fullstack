package ps.erictoader.dreamcatch

import org.koin.dsl.module
import ps.erictoader.data.dataModule
import ps.erictoader.domain.domainModule
import ps.erictoader.ui.uiModule

val appModule = module {
    includes(listOf(dataModule, domainModule, uiModule))
}