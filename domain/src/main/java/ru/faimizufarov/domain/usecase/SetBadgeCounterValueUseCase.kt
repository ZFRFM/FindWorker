package ru.faimizufarov.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.faimizufarov.domain.repository.ResultRepository

class SetBadgeCounterValueUseCase(
    val resultRepository: ResultRepository
) {
    suspend fun execute() =
        withContext(Dispatchers.IO) {
            resultRepository.setBadgeCounterValue()
        }
}