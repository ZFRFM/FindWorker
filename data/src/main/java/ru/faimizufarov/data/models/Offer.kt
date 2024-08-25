package ru.faimizufarov.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Offer(
    val id: String,
    val title: String,
    val button: Button,
    val link: String
)
