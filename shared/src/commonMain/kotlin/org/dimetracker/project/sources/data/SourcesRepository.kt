package org.dimetracker.project.sources.data

import org.dimetracker.project.articles.data.ArticleRaw
import org.dimetracker.project.articles.data.ArticlesDatasource
import org.dimetracker.project.articles.data.ArticlesService
import org.dimetracker.project.sources.Source

class SourcesRepository(
    private val datasource: SourcesDatasource,
    private val service: SourcesService
) {

    suspend fun getAllSources(): List<Source> {
        val sourcesDb = datasource.getAllSources()
        if (sourcesDb.isEmpty()) {
            datasource.clearSources()
            val fetchedSources = service.fetchSources()
            datasource.createSources(fetchedSources)
            return fetchedSources
        }
        return sourcesDb
    }
}