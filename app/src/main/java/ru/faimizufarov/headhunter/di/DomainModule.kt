package ru.faimizufarov.headhunter.di

import dagger.Module
import dagger.Provides
import ru.faimizufarov.domain.repository.ResultRepository
import ru.faimizufarov.domain.usecase.GetFavouritesUseCase
import ru.faimizufarov.domain.usecase.GetResultUseCase
import ru.faimizufarov.domain.usecase.GetVacancyUseCase
import ru.faimizufarov.domain.usecase.SetBadgeCounterValueUseCase

@Module
class DomainModule {
    @Provides
    fun provideGetResultUseCase(resultRepository: ResultRepository) =
        GetResultUseCase(resultRepository)

    @Provides
    fun provideGetVacancyUseCase(resultRepository: ResultRepository) =
        GetVacancyUseCase(resultRepository)

    @Provides
    fun provideGetFavouritesUseCase(resultRepository: ResultRepository) =
        GetFavouritesUseCase(resultRepository)

    @Provides
    fun provideSetBadgeCounterValueUseCase(resultRepository: ResultRepository) =
        SetBadgeCounterValueUseCase(resultRepository)
}