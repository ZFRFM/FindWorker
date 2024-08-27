package ru.faimizufarov.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Offer(
    val localId: Int? = null,
    val id: String? = null,
    val title: String,
    val button: Button? = null,
    val link: String
)
