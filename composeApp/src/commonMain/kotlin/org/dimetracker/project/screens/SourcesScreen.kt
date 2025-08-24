package org.dimetracker.project.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.dimetracker.project.sources.Source
import org.dimetracker.project.sources.SourcesState
import org.dimetracker.project.sources.SourcesViewModel
import org.koin.compose.koinInject

@Composable
fun SourcesScreen(
    viewModel: SourcesViewModel = koinInject(),
    onUpButtonClick: () -> Unit
) {
    val sourcesState = viewModel.sourcesState.collectAsState()

    Column {
        AppBar(onUpButtonClick)

        when (val state = sourcesState.value) {
            is SourcesState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            is SourcesState.Success -> {
                SourcesListView(sources = state.sources)
            }
            is SourcesState.Error -> {
                ErrorMessage(message = state.message)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppBar(
    onUpButtonClick: () -> Unit,
) {
    TopAppBar(
        title = { Text(text = "Sources") },
        navigationIcon = {
            IconButton(onClick = onUpButtonClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Up Button",
                )
            }
        }
    )
}

@Composable
fun SourcesListView(sources: List<Source>) {
    if (sources.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text("No sources available.")
        }
        return
    }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(sources) { source ->
            SourceItemView(source)
        }
    }
}

@Composable
fun SourceItemView(source: Source) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = source.name,
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 22.sp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        source.description.let { // Assuming 'desc' can be null
            Text(text = it)
            Spacer(modifier = Modifier.height(4.dp))
        }
         Text(
             text = source.country, // If source.origin is a direct field
             style = TextStyle(color = Color.Gray),
             modifier = Modifier.align(Alignment.End)
         )
        Spacer(modifier = Modifier.height(4.dp))
    }
}
