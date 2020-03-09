package projects.jaseem.triptimes.di

import com.facebook.stetho.okhttp3.StethoInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import projects.jaseem.triptimes.domain.ArticleRemoteSource
import projects.jaseem.triptimes.domain.repository.ArticleRepository
import projects.jaseem.triptimes.domain.nytimesservice.NYTimesApiService
import projects.jaseem.triptimes.ui.screens.articlesearch.ArticleSearchViewModel
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class NetworkModule {

    @Provides
    fun provideArticleSearchViewModel(): ArticleSearchViewModel =
        ArticleSearchViewModel(
            provideArticleRepository()
        )

    @Provides
    @Singleton
    fun provideArticleRepository(): ArticleRepository =
        ArticleRepository(
            provideNyTimesApiService()
        )

    @Provides
    @Singleton
    fun provideArticleRemoteSource(): ArticleRemoteSource =
        ArticleRemoteSource(provideNyTimesApiService())

    @Provides
    fun provideNyTimesApiService(): NYTimesApiService =
        provideRetrofit().create(NYTimesApiService::class.java)

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .client(provideOkHttp())
            .baseUrl("https://api.nytimes.com/svc/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient =
        OkHttpClient().newBuilder()
            .addNetworkInterceptor(StethoInterceptor())
            .build()

}