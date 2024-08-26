package ru.faimizufarov.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.faimizufarov.domain.repository.ResultRepository

class GetVacancyUseCase(
    val resultRepository: ResultRepository
) {
    suspend fun execute(id: String) =
        withContext(Dispatchers.IO) {
            resultRepository.requestVacancy(id)
        }
}