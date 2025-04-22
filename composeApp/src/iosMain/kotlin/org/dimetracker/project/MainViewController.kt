package org.dimetracker.project

import androidx.compose.ui.window.ComposeUIViewController
import org.dimetracker.project.config.dependencyInjection.initKoin

fun MainViewController() = ComposeUIViewController {
    Platform().logSystemInfo()
    initKoin()
    App()
}

fun getPlatform(): Platform {
    return Platform();
}