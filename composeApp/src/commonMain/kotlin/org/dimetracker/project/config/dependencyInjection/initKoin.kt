package org.dimetracker.project.config.dependencyInjection

import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(sharedModule + viewModelModule)
    }
}