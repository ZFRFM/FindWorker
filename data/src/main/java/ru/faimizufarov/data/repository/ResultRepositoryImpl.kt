package ru.faimizufarov.data.repository

import android.content.Context
import ru.faimizufarov.data.network.AppApi
import ru.faimizufarov.domain.repository.ResultRepository
import ru.faimizufarov.domain.models.Result
import ru.faimizufarov.domain.models.Vacancy

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

    override suspend fun setBadgeCounterEmitValue(emitValue: Int) {
        TODO("Not yet implemented")
    }

}