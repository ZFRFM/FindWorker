package ru.faimizufarov.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Experience(
    val previewText: String? = null,
    val text: String? = null
)
