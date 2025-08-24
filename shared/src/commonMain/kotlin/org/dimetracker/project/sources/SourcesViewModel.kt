package org.dimetracker.project.sources

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.dimetracker.project.BaseViewModel
import org.dimetracker.project.sources.data.SourcesUseCase

class SourcesViewModel(
    private val useCase: SourcesUseCase
) : BaseViewModel() {
    private val _sourcesState: MutableStateFlow<SourcesState> =
        MutableStateFlow(SourcesState.Loading)
    val sourcesState: StateFlow<SourcesState> get() = _sourcesState

    init {
        getSources()
    }

    fun getSources(forcedRefresh: Boolean = false) {
        scope.launch {
            try {
                _sourcesState.emit(SourcesState.Loading)
                val articleData = useCase.getSources();
                _sourcesState.emit(SourcesState.Success(articleData))
            } catch (e: Exception) {
                _sourcesState.emit(SourcesState.Error("Failed to load articles"))
            }
        }
    }
}