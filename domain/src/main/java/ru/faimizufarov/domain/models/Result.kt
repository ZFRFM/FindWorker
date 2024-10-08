package ru.faimizufarov.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Result(
    val offers: List<Offer>,
    val vacancies: List<Vacancy>
)
