package projects.jaseem.triptimes.domain

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import projects.jaseem.triptimes.domain.nytimesservice.NYTimesApiService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Singleton
object RestApi {

    val nyTimesClient : NYTimesApiService

    init {
        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val okHttpClient = OkHttpClient().newBuilder()
            .addInterceptor(interceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://api.nytimes.com/svc/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        nyTimesClient = retrofit.create(NYTimesApiService::class.java)
    }

}