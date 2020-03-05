package projects.jaseem.triptimes.di

import dagger.Module
import dagger.Provides
import projects.jaseem.triptimes.domain.RestApi
import javax.inject.Named


@Module
class ApplicationModule {

    @Provides
    @Named("restApi")
    fun provideRestApi(): RestApi =
        RestApi

}