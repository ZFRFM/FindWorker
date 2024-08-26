package ru.faimizufarov.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Experience(
    val previewText: String? = null,
    val text: String? = null
)
