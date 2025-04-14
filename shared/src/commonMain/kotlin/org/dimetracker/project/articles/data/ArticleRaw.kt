package org.dimetracker.project.articles.data

import kotlinx.serialization.SerialName

@Serializable
data class ArticleRaw (
    val title: String,
    val description: String?,
    @SerialName("publishedAt")
    val date: String,
    @SerialName("urlToImage")
    val imageUrl: String?
)
