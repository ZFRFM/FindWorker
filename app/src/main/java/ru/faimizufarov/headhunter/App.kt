package ru.faimizufarov.headhunter

import android.app.Application
import ru.faimizufarov.headhunter.di.AppComponent
import ru.faimizufarov.headhunter.di.AppModule
import ru.faimizufarov.headhunter.di.DaggerAppComponent
import ru.faimizufarov.search.di.SearchComponent
import ru.faimizufarov.search.di.SearchComponentProvider
import ru.faimizufarov.vacancy_page.di.VacancyPageComponent
import ru.faimizufarov.vacancy_page.di.VacancyPageComponentProvider

class App : Application(), SearchComponentProvider, VacancyPageComponentProvider {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(AppModule(this))
        appComponent.inject(this)
    }

    override fun provideSearchComponent(): SearchComponent = appComponent

    override fun provideVacancyPageComponent(): VacancyPageComponent = appComponent
}