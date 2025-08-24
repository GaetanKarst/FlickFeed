package org.dimetracker.project.articles

import org.dimetracker.project.articles.data.Article

sealed class ArticlesState {
    data object Loading : ArticlesState()
    data class Success(val articles: List<Article>): ArticlesState()
    data class Error(val message: String): ArticlesState()
}