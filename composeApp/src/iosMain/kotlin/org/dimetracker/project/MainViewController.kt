package org.dimetracker.project

import androidx.compose.ui.window.ComposeUIViewController

fun MainViewController() = ComposeUIViewController {
    Platform().logSystemInfo()
    initKoin()
    App()
}

fun getPlatform(): Platform {
    return Platform();
}