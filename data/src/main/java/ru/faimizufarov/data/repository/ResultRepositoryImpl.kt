package ru.faimizufarov.data.repository

import ru.faimizufarov.data.network.AppApi
import ru.faimizufarov.domain.repository.ResultRepository
import ru.faimizufarov.domain.models.Result

class ResultRepositoryImpl: ResultRepository {

    override suspend fun requestResult(): Result {
        return AppApi.retrofitService.getResult()
    }

    override suspend fun setBadgeCounterEmitValue(emitValue: Int) {
        TODO("Not yet implemented")
    }

}