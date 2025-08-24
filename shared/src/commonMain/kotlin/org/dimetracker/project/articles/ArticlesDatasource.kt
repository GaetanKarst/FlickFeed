package org.dimetracker.project.articles

import gaetan.karst.dailypulse.db.FlickFeedDatabase
import org.dimetracker.project.articles.data.ArticleRaw

class ArticlesDatasource(private val database: FlickFeedDatabase) {
    fun getAllArticles(): List<ArticleRaw> {
        return database.flickFeedDatabaseQueries.selectAllArticles(::mapToArticleRaw).executeAsList()
    }

    private fun mapToArticleRaw(
        title:String,
        desc: String?,
        date: String,
        imageUrl: String?
    ) : ArticleRaw {
        return ArticleRaw(
            title,
            desc,
            date,
            imageUrl
        )
    }

    fun insertArticles(articles: List<ArticleRaw>) {
        database.flickFeedDatabaseQueries.transaction {
            articles.forEach { articleRaw ->
                insertArticle(articleRaw)
            }
        }
    }

    private fun insertArticle(articleRaw: ArticleRaw) {
        database.flickFeedDatabaseQueries.insertArticle(
            articleRaw.title,
            articleRaw.description,
            articleRaw.date,
            articleRaw.imageUrl
        )
    }

    fun deleteArticles() {
        database.flickFeedDatabaseQueries.removeAllArticles()
    }
}