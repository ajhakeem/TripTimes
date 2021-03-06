package projects.jaseem.triptimes.di

import com.facebook.stetho.okhttp3.StethoInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import projects.jaseem.triptimes.domain.repository.ArticleRepository
import projects.jaseem.triptimes.domain.nytimesservice.NYTimesApiService
import projects.jaseem.triptimes.domain.usecase.SearchArticlesUseCase
import projects.jaseem.triptimes.ui.screens.articlesearch.ArticleSearchViewModel
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
class NetworkModule {

    @Provides
    fun provideArticleSearchViewModel(): ArticleSearchViewModel =
        ArticleSearchViewModel(
            provideSearchArticleUseCase()
        )

    @Provides
    @Singleton
    fun provideSearchArticleUseCase(): SearchArticlesUseCase =
        SearchArticlesUseCase(provideArticleRepository())

    @Provides
    @Singleton
    fun provideArticleRepository(): ArticleRepository =
        ArticleRepository(
            provideNyTimesApiService()
        )

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
            .connectTimeout(8, TimeUnit.SECONDS)
            .readTimeout(8, TimeUnit.SECONDS)
            .writeTimeout(8, TimeUnit.SECONDS)
            .build()

}