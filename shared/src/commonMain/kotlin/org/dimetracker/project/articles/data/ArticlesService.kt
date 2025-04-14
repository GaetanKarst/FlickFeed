package org.dimetracker.project.articles.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ArticlesService(private val httpClient: HttpClient) {
    private val COUNTRY = "us"
    private val CATEGORY = "technology"
    private val TESTAPIKEY = "1bd3a6ab52664ea995a196be83aea9ca"

    suspend fun fetchArticles(): List<ArticleRaw> {
        val response: ArticlesResponse = httpClient.get("https://newsapi.org/v2/top-headlines?country=$COUNTRY&category=$CATEGORY&apiKey=$TESTAPIKEY").body()
        return response.articles
    }

}