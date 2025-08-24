package org.dimetracker.project.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.List
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
actual fun Loader() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.width(64.dp),
            color = MaterialTheme.colors.onBackground
        )
    }
}

@Composable
actual fun AppBar(
    title: String,
    onAboutButtonClick: () -> Unit,
    onSourcesButtonClick: () -> Unit
) {
    TopAppBar(
        title = { Text(text = title) },
        actions = {
            IconButton(onClick = onSourcesButtonClick) {
                Icon(
                    imageVector = Icons.Outlined.List,
                    contentDescription = "Sources Button"
                )
            }
            IconButton(onClick = onAboutButtonClick) {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = "About Button"
                )
            }
        }
    )
}