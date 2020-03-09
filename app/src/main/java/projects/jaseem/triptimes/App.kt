package projects.jaseem.triptimes

import android.app.Activity
import android.app.Application
import android.content.Context
import com.facebook.stetho.Stetho
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import projects.jaseem.triptimes.di.ApplicationComponent
import projects.jaseem.triptimes.di.DaggerApplicationComponent
import javax.inject.Inject


class App : Application(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    val appComponent: ApplicationComponent by lazy {
        initComponent()
    }

    private fun initComponent(): ApplicationComponent = DaggerApplicationComponent.create()

    override fun onCreate() {
        super.onCreate()
        appContext = this

        appComponent.inject(this)
        Stetho.initializeWithDefaults(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

}

lateinit var appContext: Context
    internal set