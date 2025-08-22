package org.dimetracker.project.articles

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.dimetracker.project.BaseViewModel
import org.dimetracker.project.articles.data.ArticlesUseCase

class ArticlesViewModel(
    private val useCase: ArticlesUseCase
) : BaseViewModel() {
    private val _articlesState: MutableStateFlow<ArticlesState> =
        MutableStateFlow(ArticlesState.Loading)
    val articlesState: StateFlow<ArticlesState> get() = _articlesState

    init {
        getArticles()
    }

    fun getArticles(forcedRefresh: Boolean = false) {
        scope.launch {
            try {
                val articleData = useCase.getArticles(forcedRefresh);
                _articlesState.emit(ArticlesState.Success(articleData))
            } catch (e: Exception) {
                _articlesState.emit(ArticlesState.Error("Failed to load articles"))
            }
        }
    }
}