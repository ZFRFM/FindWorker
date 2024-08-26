package ru.faimizufarov.headhunter.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.faimizufarov.domain.usecase.GetResultUseCase
import ru.faimizufarov.domain.usecase.GetVacancyUseCase
import ru.faimizufarov.search.ui.SearchViewModelFactory
import ru.faimizufarov.vacancy_page.ui.VacancyPageViewModelFactory
import javax.inject.Singleton

@Module
class AppModule(val context: Context) {
    @Provides
    @Singleton
    fun provideContext() = context

    @Provides
    fun provideSearchViewModelFactory(
        getResultUseCase: GetResultUseCase
    ) = SearchViewModelFactory(
        getResultUseCase = getResultUseCase
    )

    @Provides
    fun provideVacancyPageViewModelFactory(
        getVacancyUseCase: GetVacancyUseCase
    ) = VacancyPageViewModelFactory(
        getVacancyUseCase = getVacancyUseCase
    )
}