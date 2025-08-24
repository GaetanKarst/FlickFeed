package org.dimetracker.project.sources.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.dimetracker.project.sources.Source

@Serializable
data class SourcesResponse(
    val status: String,
    val sources: List<Source>
)