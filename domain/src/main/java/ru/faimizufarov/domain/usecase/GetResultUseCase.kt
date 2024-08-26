package ru.faimizufarov.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.faimizufarov.domain.repository.ResultRepository

class GetResultUseCase(
    val resultRepository: ResultRepository
) {
    suspend fun execute() =
        withContext(Dispatchers.IO) {
            resultRepository.requestResult()
        }
}