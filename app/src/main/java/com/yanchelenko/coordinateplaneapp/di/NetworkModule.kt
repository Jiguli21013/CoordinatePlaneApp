package com.yanchelenko.coordinateplaneapp.di

import com.yanchelenko.coordinateplaneapp.data.remote.api.PointsApi
import com.yanchelenko.coordinateplaneapp.data.remote.services.points.PointService
import com.yanchelenko.coordinateplaneapp.data.remote.services.points.PointServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val TIMEOUT = 20L

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val HTTP_SCHEME = "https://"
    private const val API_PATH = "hr-challenge.dev.tapyou.com/api/test/"

    @Provides
    @Singleton
    fun provideClient(httpLoggingInterceptor: HttpLoggingInterceptor) = OkHttpClient.Builder().apply {
        followRedirects(false)
        followSslRedirects(false)
        connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        readTimeout(TIMEOUT, TimeUnit.SECONDS)
        writeTimeout(TIMEOUT, TimeUnit.SECONDS)
        addInterceptor(httpLoggingInterceptor)
    }.build()

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl("$HTTP_SCHEME$API_PATH")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun provideAuthApi(retrofit: Retrofit): PointsApi = retrofit.create(PointsApi::class.java)

    @Provides
    fun provideAuthService(api: PointsApi): PointService = PointServiceImpl(api)
}