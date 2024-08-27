package ru.faimizufarov.headhunter.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.faimizufarov.domain.usecase.GetFavouritesUseCase
import ru.faimizufarov.domain.usecase.GetResultUseCase
import ru.faimizufarov.domain.usecase.GetVacancyUseCase
import ru.faimizufarov.domain.usecase.UpdateFavouriteVacancyUseCase
import ru.faimizufarov.favourite.ui.FavouriteViewModelFactory
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
        getResultUseCase: GetResultUseCase,
        updateFavouriteVacancyUseCase: UpdateFavouriteVacancyUseCase
    ) = SearchViewModelFactory(
        getResultUseCase = getResultUseCase,
        updateFavouriteVacancyUseCase = updateFavouriteVacancyUseCase
    )

    @Provides
    fun provideVacancyPageViewModelFactory(
        getVacancyUseCase: GetVacancyUseCase
    ) = VacancyPageViewModelFactory(
        getVacancyUseCase = getVacancyUseCase
    )

    @Provides
    fun provideFavouriteViewModelFactory(
        getFavouritesUseCase: GetFavouritesUseCase,
        updateFavouriteVacancyUseCase: UpdateFavouriteVacancyUseCase
    ) = FavouriteViewModelFactory(
        getFavouritesUseCase = getFavouritesUseCase,
        updateFavouriteVacancyUseCase = updateFavouriteVacancyUseCase
    )
}