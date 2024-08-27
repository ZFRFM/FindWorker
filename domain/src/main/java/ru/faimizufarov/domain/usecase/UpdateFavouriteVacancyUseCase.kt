package ru.faimizufarov.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.faimizufarov.domain.models.Vacancy
import ru.faimizufarov.domain.repository.ResultRepository

class UpdateFavouriteVacancyUseCase(
    val resultRepository: ResultRepository
) {
    suspend fun execute(vacancy: Vacancy) =
        withContext(Dispatchers.IO) {
            resultRepository.updateVacancy(vacancy)
        }
}