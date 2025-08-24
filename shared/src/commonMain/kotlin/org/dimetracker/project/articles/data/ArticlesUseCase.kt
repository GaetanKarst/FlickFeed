package org.dimetracker.project.articles.data

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn
import org.dimetracker.project.articles.data.Article
import org.dimetracker.project.articles.data.ArticlesRepository
import kotlin.math.abs

class ArticlesUseCase(private val repo: ArticlesRepository) {

    suspend fun getArticles(forcedRefresh: Boolean): List<Article> {
        val articlesRaw = repo.getArticles(forcedRefresh)
        return mapArticles(articlesRaw)
    }

    private fun mapArticles(articlesRaw: List<ArticleRaw>): List<Article> {
        return articlesRaw.map { raw ->
            Article(
                raw.title,
                raw.description ?: "Click to find out more",
                getDaysAgo(raw.date),
                raw.imageUrl ?: "https://www.vecteezy.com/vector-art/22014063-missing-picture-page-for-website-design-or-mobile-app-design-no-image-available-icon-vector"
            )
        }
    }

    private fun getDaysAgo(date: String): String {
        val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
        val days = today.daysUntil(
            Instant.parse(date).toLocalDateTime(TimeZone.currentSystemDefault()).date
        )

        val result = when {
            abs(days) > 1 -> "${abs(days)} days ago"
            abs(days) == 1 -> "Yesterday"
            else -> "Today"
        }

        return result
    }
}