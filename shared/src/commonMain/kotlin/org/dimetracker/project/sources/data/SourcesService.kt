package org.dimetracker.project.sources.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import org.dimetracker.project.sources.Source

class SourcesService(private val httpClient: HttpClient) {
    private val TESTAPIKEY = "1bd3a6ab52664ea995a196be83aea9ca"

    suspend fun fetchSources(): List<Source> {
        val response: SourcesResponse = httpClient.get("https://newsapi.org/v2/top-headlines/sources?&apiKey=$TESTAPIKEY").body()
        return response.sources
    }

}