package ru.faimizufarov.headhunter.di

import dagger.Module
import dagger.Provides
import ru.faimizufarov.domain.repository.ResultRepository
import ru.faimizufarov.domain.usecase.GetResultUseCase

@Module
class DomainModule {
    @Provides
    fun provideGetResultUseCase(resultRepository: ResultRepository) =
        GetResultUseCase(resultRepository)
}