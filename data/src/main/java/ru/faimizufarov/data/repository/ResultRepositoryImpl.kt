package ru.faimizufarov.data.repository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.faimizufarov.data.models.Result
import ru.faimizufarov.data.network.AppApi
import ru.faimizufarov.domain.repository.ResultRepository

class ResultRepositoryImpl: ResultRepository {

    private val _resultFlow = MutableStateFlow<Result?>(null)
    val resultFlow: StateFlow<Result?> = _resultFlow

    override suspend fun requestResult() {
        val result = AppApi.retrofitService.getResult()
        _resultFlow.emit(result)
    }

    override suspend fun setBadgeCounterEmitValue(emitValue: Int) {
        TODO("Not yet implemented")
    }

}