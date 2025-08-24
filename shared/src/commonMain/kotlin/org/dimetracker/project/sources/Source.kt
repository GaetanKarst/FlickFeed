package org.dimetracker.project.sources

import kotlinx.serialization.Serializable

@Serializable
data class Source(
    val id: String,
    val name: String,
    val description: String,
    val url: String?,
    val country: String,
    val category: String,
    val language: String,
    )