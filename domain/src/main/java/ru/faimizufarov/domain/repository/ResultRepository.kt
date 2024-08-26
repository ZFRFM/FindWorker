package ru.faimizufarov.domain.repository

import ru.faimizufarov.domain.models.Result

interface ResultRepository {
    suspend fun requestResult(): Result

    suspend fun setBadgeCounterEmitValue(emitValue: Int)
}