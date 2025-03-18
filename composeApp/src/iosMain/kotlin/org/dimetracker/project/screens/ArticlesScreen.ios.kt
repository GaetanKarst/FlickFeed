package org.dimetracker.project.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.interop.UIKitView
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.UIKitInteropProperties
import androidx.compose.ui.viewinterop.UIKitView
import kotlinx.cinterop.ExperimentalForeignApi
import platform.CoreGraphics.CGRectMake
import platform.UIKit.*

fun Color.toUIColor(): UIColor {
    val red = this.red
    val green = this.green
    val blue = this.blue
    val alpha = this.alpha
    return UIColor.colorWithRed(red.toDouble(), green.toDouble(), blue.toDouble(), alpha.toDouble())
}

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun Loader() {
    val indicatorColor = MaterialTheme.colors.onBackground.toUIColor()

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center // Ensure centering
    ) {
        UIKitView<UIActivityIndicatorView>(
            factory = {
                UIActivityIndicatorView().apply {
                    activityIndicatorViewStyle = UIActivityIndicatorViewStyleLarge
                    color = indicatorColor
                    startAnimating()
                }
            },
            modifier = Modifier.size(64.dp), // Explicit size
            properties = UIKitInteropProperties(
                isInteractive = true,
                isNativeAccessibilityEnabled = true
            )
        )
    }
}

