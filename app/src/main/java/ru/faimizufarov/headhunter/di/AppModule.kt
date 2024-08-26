package ru.faimizufarov.headhunter.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.faimizufarov.domain.usecase.GetResultUseCase
import ru.faimizufarov.search.ui.SearchViewModelFactory
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
}