package ru.faimizufarov.headhunter.di

import android.app.Application
import dagger.Component
import ru.faimizufarov.favourite.di.FavouriteComponent
import ru.faimizufarov.search.di.SearchComponent
import ru.faimizufarov.vacancy_page.di.VacancyPageComponent
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DomainModule::class, DataModule::class])
interface AppComponent:
    SearchComponent,
    VacancyPageComponent,
    FavouriteComponent
{
    fun inject(application: Application)

    @Component.Factory
    interface Factory {
        fun create(appModule: AppModule): AppComponent
    }
}