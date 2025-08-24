package org.dimetracker.project.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.UIKitInteropProperties
import androidx.compose.ui.viewinterop.UIKitView
import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.ObjCAction
import platform.Foundation.NSSelectorFromString
import platform.UIKit.UIActivityIndicatorView
import platform.UIKit.UIActivityIndicatorViewStyleLarge
import platform.UIKit.UIBarButtonItem
import platform.UIKit.UIBarButtonItemStyle
import platform.UIKit.UIColor
import platform.UIKit.UINavigationBar
import platform.UIKit.UINavigationItem
import platform.UIKit.UINavigationItemLargeTitleDisplayMode
import platform.UIKit.UIView
import platform.UIKit.labelColor
import platform.UIKit.systemBackgroundColor
import platform.darwin.NSObject

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
        contentAlignment = Alignment.Center
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

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun AppBar(title: String, onAboutButtonClick: () -> Unit) {
    UIKitView(
        factory = {
            val viewController = AboutButtonHandler(onAboutButtonClick)
            val navigationBar = UINavigationBar().apply {
                translatesAutoresizingMaskIntoConstraints = false
                barTintColor = UIColor.systemBackgroundColor
                tintColor = UIColor.labelColor

                val navigationItem = UINavigationItem(title).apply {
                    largeTitleDisplayMode =
                        UINavigationItemLargeTitleDisplayMode.UINavigationItemLargeTitleDisplayModeAlways

                    rightBarButtonItem = UIBarButtonItem(
                        title = "About",
                        style = UIBarButtonItemStyle.UIBarButtonItemStylePlain,
                        target = viewController,
                        action = NSSelectorFromString("aboutButtonTapped")
                    )
                }

                setItems(listOf(navigationItem), false)
            }

            // Add navigationBar to the view
            val rootView = UIView().apply {
                backgroundColor = UIColor.systemBackgroundColor
                addSubview(navigationBar)

                // Setup constraints to position it at the top
                navigationBar.topAnchor.constraintEqualToAnchor(this.topAnchor).active = true
                navigationBar.leadingAnchor.constraintEqualToAnchor(this.leadingAnchor).active =
                    true
                navigationBar.trailingAnchor.constraintEqualToAnchor(this.trailingAnchor).active =
                    true
            }
            return@UIKitView rootView
        },
        modifier = Modifier.fillMaxWidth().height(56.dp)
    )
}

class AboutButtonHandler(private val onClick: () -> Unit) : NSObject() {
    @OptIn(BetaInteropApi::class)
    @ObjCAction
    fun aboutButtonTapped() {
        onClick()
    }
}