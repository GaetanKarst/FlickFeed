package org.dimetracker.project.articles

import org.dimetracker.project.articles.data.ArticleRaw
import org.dimetracker.project.articles.data.ArticlesService

class ArticlesRepository(
    private val datasource: ArticlesDatasource,
    private val service: ArticlesService) {

    suspend fun getArticles(): List<ArticleRaw> {
        val articles = datasource.getAllArticles()
        ("articles in db or not: " + articles.size)

        if (articles.isEmpty()) {
            val fetchedArticles = service.fetchArticles()
            datasource.insertArticles(fetchedArticles)
            return fetchedArticles
        }

        return articles;
    }
}