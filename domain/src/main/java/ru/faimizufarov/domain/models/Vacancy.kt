package ru.faimizufarov.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Vacancy(
    val id: String,
    val lookingNumber: Int? = null,
    val title: String? = null,
    val address: Address? = null,
    val company: String? = null,
    val experience: Experience? = null,
    val publishedDate: String? = null,
    val isFavorite: Boolean? = null,
    val salary: Salary? = null,
    val schedules: List<String>? = null,
    val appliedNumber: Int? = null,
    val description: String? = null,
    val responsibilities: String? = null,
    val questions: List<String>? = null
)
