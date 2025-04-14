package org.dimetracker.project.articles.data

import kotlinx.serialization.SerialName

@Serializable
data class ArticlesResponse (
    val status: String,
    @SerialName("totalResults")
    val results: Int,
    val articles: List<ArticleRaw>
)