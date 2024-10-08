package ru.faimizufarov.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Address(
    val town: String? = null,
    val street: String? = null,
    val house: String? = null
)
