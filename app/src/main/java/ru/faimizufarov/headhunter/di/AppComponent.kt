package ru.faimizufarov.headhunter.di

import android.app.Application
import dagger.Component
import ru.faimizufarov.search.di.SearchComponent
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DomainModule::class, DataModule::class])
interface AppComponent: SearchComponent {
    fun inject(application: Application)

    @Component.Factory
    interface Factory {
        fun create(appModule: AppModule): AppComponent
    }
}