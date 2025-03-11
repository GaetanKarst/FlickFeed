package org.dimetracker.project.articles

sealed class ArticlesState {
    data object Loading : ArticlesState()
    data class Success(val articles: List<Article>): ArticlesState()
    data class Error(val message: String): ArticlesState()
}