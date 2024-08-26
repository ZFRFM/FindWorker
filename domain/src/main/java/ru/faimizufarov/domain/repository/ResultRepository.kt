package ru.faimizufarov.domain.repository

import ru.faimizufarov.domain.models.Result
import ru.faimizufarov.domain.models.Vacancy

interface ResultRepository {
    suspend fun requestResult(): Result

    suspend fun requestVacancy(id: String): Vacancy

    suspend fun setBadgeCounterEmitValue(emitValue: Int)
}