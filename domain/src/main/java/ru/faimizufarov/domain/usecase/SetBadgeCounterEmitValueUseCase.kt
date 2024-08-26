package ru.faimizufarov.domain.usecase

import ru.faimizufarov.domain.repository.ResultRepository

class SetBadgeCounterEmitValueUseCase(
    val resultRepository: ResultRepository
) {
    suspend fun execute(emitValue: Int) {
        resultRepository.setBadgeCounterEmitValue(emitValue)
    }
}