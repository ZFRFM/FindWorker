package ru.faimizufarov.data.repository

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.faimizufarov.data.network.AppApi
import ru.faimizufarov.domain.repository.ResultRepository
import ru.faimizufarov.domain.models.Result
import ru.faimizufarov.domain.models.Vacancy
import kotlin.coroutines.suspendCoroutine

class ResultRepositoryImpl(
    private val context: Context
    // FIXME: Если не успею ввести БД, то контекст уберу
): ResultRepository {

    override suspend fun requestResult(): Result {
        return AppApi.retrofitService.getResult()
    }

    override suspend fun requestVacancy(id: String): Vacancy {
        val result = AppApi.retrofitService.getResult()
        val vacancy = result.vacancies.first { vacancy ->
            vacancy.id == id
        }
        return vacancy
    }

    override suspend fun requestFavouriteVacancies(): List<Vacancy> {
        val result = AppApi.retrofitService.getResult()
        val favouriteVacanciesList = result.vacancies.filter { vacancy ->
            vacancy.isFavorite == true
        }
        return favouriteVacanciesList
    }

    override suspend fun setBadgeCounterValue(): Int {
        return requestFavouriteVacancies().size
    }

}