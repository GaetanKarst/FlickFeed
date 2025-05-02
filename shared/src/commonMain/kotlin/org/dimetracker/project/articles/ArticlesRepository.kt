package org.dimetracker.project.articles

import org.dimetracker.project.articles.data.ArticleRaw
import org.dimetracker.project.articles.data.ArticlesService

class ArticlesRepository(
    private val datasource: ArticlesDatasource,
    private val service: ArticlesService
) {

    suspend fun getArticles(forcedRefresh: Boolean): List<ArticleRaw> {
        if (forcedRefresh) {
            datasource.deleteArticles()
            fetchNewArticles()
        }

        val articles = datasource.getAllArticles()
        ("articles in db or not: " + articles.size)

        if (articles.isEmpty()) {
            fetchNewArticles()
        }

        return articles;
    }

    suspend fun fetchNewArticles(): List<ArticleRaw> {
        val fetchedArticles = service.fetchArticles()
        datasource.insertArticles(fetchedArticles)
        return fetchedArticles
    }
}