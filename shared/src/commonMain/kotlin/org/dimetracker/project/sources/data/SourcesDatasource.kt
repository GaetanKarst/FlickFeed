package org.dimetracker.project.sources.data

import gaetan.karst.dailypulse.db.FlickFeedDatabase
import org.dimetracker.project.sources.Source

class SourcesDatasource(private val database: FlickFeedDatabase) {
    fun getAllSources(): List<Source> {
        return database.flickFeedDatabaseQueries.selectAllSources { id, name, description, url, country, category, language ->
            mapToSource(id, name, description, url, country, category, language)
        }.executeAsList()
    }

    fun clearSources() =
        database.flickFeedDatabaseQueries.removeAllSources()

    private fun mapToSource(
        id: String,
        name: String,
        description: String,
        url: String?,
        country: String,
        category: String,
        language: String,
    ): Source {
        return Source(
            id,
            name,
            description,
            url,
            country,
            category,
            language
        )
    }

    internal fun createSources(sources: List<Source>) {
        database.flickFeedDatabaseQueries.transaction {
            sources.forEach { source ->
                insertSource(source)
            }
        }
    }

    private fun insertSource(source: Source) {
        database.flickFeedDatabaseQueries.insertSource(
            source.id,
            source.name,
            source.description,
            source.url,
            source.country,
            source.category,
            source.language

        )
    }


}