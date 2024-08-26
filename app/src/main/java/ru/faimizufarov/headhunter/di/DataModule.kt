package ru.faimizufarov.headhunter.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.faimizufarov.data.repository.ResultRepositoryImpl
import ru.faimizufarov.domain.repository.ResultRepository
import javax.inject.Singleton

@Module
class DataModule {
    @Provides
    @Singleton
    fun provideResultRepository(context: Context): ResultRepository = ResultRepositoryImpl(context)
}