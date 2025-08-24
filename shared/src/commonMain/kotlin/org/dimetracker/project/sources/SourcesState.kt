package org.dimetracker.project.sources

sealed class SourcesState {
    data object Loading : SourcesState()
    data class Success(val sources: List<Source>) : SourcesState()
    data class Error(val message: String) : SourcesState()
}