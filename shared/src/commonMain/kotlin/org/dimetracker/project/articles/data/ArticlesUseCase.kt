package org.dimetracker.project.articles.data

import org.dimetracker.project.articles.Article

class ArticlesUseCase(private val service: ArticlesService) {

    suspend fun getArticles(): List<Article> {
        val articlesRaw = service.fetchArticles();
        return mapArticles(articlesRaw)
    }

    private fun mapArticles(articlesRaw: List<ArticleRaw>): List<Article> {
        return articlesRaw.map { raw ->
            Article(
                raw.title,
                raw.description ?: "Click to find out more",
                raw.date,
                raw.imageUrl ?: "https://www.vecteezy.com/vector-art/22014063-missing-picture-page-for-website-design-or-mobile-app-design-no-image-available-icon-vector"
            )
        }
    }
}