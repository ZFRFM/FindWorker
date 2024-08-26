package ru.faimizufarov.domain.repository

interface ResultRepository {
    suspend fun requestResult()

    suspend fun setBadgeCounterEmitValue(emitValue: Int)
}