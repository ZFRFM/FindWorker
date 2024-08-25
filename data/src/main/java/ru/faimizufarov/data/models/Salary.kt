package ru.faimizufarov.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Salary(
    val short: String? = null,
    val full: String? = null
)
