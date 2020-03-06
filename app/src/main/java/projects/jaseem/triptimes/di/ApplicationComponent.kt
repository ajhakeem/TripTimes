package projects.jaseem.triptimes.di

import dagger.Component
import dagger.android.AndroidInjectionModule
import projects.jaseem.triptimes.App
import javax.inject.Singleton


@Singleton
@Component (modules = [
    NetworkModule::class,
    ViewModelModule::class,
    AndroidInjectionModule::class,
    ActivityModule::class
])
interface ApplicationComponent {

    fun inject(app: App)

}