package ru.faimizufarov.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Salary(
    val short: String? = null,
    val full: String? = null
)
