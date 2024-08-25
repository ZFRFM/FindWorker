package ru.faimizufarov.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Offer(
    val id: String? = null,
    val title: String,
    val button: Button? = null,
    val link: String? = null
)
