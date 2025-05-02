package org.dimetracker.project

import androidx.compose.ui.window.ComposeUIViewController
import org.dimetracker.project.config.dependencyInjection.databaseModule
import org.dimetracker.project.config.dependencyInjection.sharedModule
import org.dimetracker.project.config.dependencyInjection.viewModelModule
import org.koin.core.context.startKoin

fun MainViewController() = ComposeUIViewController {
    Platform().logSystemInfo()
    initKoin()
    App()
}

fun getPlatform(): Platform {
    return Platform();
}

fun initKoin() {
    val modules = sharedModule + viewModelModule + databaseModule
    startKoin {
        modules(modules)
    }
}