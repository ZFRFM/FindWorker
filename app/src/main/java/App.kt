import android.app.Application
import ru.faimizufarov.headhunter.di.AppComponent
import ru.faimizufarov.headhunter.di.AppModule

class App : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(AppModule(this))
        appComponent.inject(this)
    }
}