package org.dimetracker.project.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.interop.UIKitView
import androidx.compose.ui.viewinterop.UIKitInteropProperties
import androidx.compose.ui.viewinterop.UIKitView
import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.ObjCAction
import platform.UIKit.*
import platform.objc.sel_registerName

@OptIn(ExperimentalForeignApi::class, ExperimentalComposeUiApi::class, BetaInteropApi::class)
@Composable
actual fun AppBar(
    title: String,
    onAboutButtonClick: () -> Unit
) {
    UIKitView(
        factory = {
            val viewController = object : UIViewController(nibName = null, bundle = null) {
                var buttonAction: (() -> Unit)? = null

//                @ObjCAction
//                fun onAboutButtonTapped() {
//                    buttonAction?.invoke()
//                }

                override fun viewDidLoad() {
                    super.viewDidLoad()
                    navigationItem.title = title
                    navigationItem.largeTitleDisplayMode =
                        UINavigationItemLargeTitleDisplayMode.UINavigationItemLargeTitleDisplayModeAutomatic

                    val aboutButton = UIBarButtonItem(
                        title = "About",
                        style = UIBarButtonItemStyle.UIBarButtonItemStylePlain,
                        target = this,
                        action = sel_registerName("onAboutButtonTapped")
                    )
                    navigationItem.rightBarButtonItem = aboutButton
                }
            }

            viewController.buttonAction = onAboutButtonClick

            UINavigationController(viewController).apply {
                navigationBar.prefersLargeTitles = true
            }.view
        }
    )
}