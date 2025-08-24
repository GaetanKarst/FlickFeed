package org.dimetracker.project.sources.data

import org.dimetracker.project.sources.Source

class SourcesUseCase(private val repo: SourcesRepository) {

    suspend fun getSources(): List<Source> {
        val sourcesRaw = repo.getAllSources()

        return mapSources(sourcesRaw)
    }

    private fun mapSources(sourcesRaw: List<Source>): List<Source> = sourcesRaw.map { raw ->
        Source(
            raw.id,
            raw.name,
            raw.description,
            raw.url,
            raw.category,
            raw.country,
            raw.language
        )
    }

    private fun mapOrigin(raw: Source) = "${raw.country} - ${raw.language}"
}