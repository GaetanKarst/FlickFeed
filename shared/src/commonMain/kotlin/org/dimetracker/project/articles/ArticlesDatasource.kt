package org.dimetracker.project.articles

import gaetan.karst.dailypulse.db.DailyPulseDatabase
import org.dimetracker.project.articles.data.ArticleRaw

class ArticlesDatasource(private val database: DailyPulseDatabase) {
    fun getAllArticles(): List<ArticleRaw> {
        return database.sqlQueries.selectAllArticles(::mapToArticleRaw).executeAsList()
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
        database.sqlQueries.transaction {
            articles.forEach { articleRaw ->
                insertArticle(articleRaw)
            }
        }
    }

    private fun insertArticle(articleRaw: ArticleRaw) {
        database.sqlQueries.insertArticle(
            articleRaw.title,
            articleRaw.description,
            articleRaw.date,
            articleRaw.imageUrl
        )
    }

    fun deleteArticles() {
        database.sqlQueries.removeAllArticles()
    }
}