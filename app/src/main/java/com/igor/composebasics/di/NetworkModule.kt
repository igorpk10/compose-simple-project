package com.igor.composebasics.di

import com.igor.composebasics.BuildConfig
import com.igor.composebasics.data.repositories.PictureRepository
import com.igor.composebasics.di.interceptors.AuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val TIME_OUT_SECONDS: Long = 15L
    private const val BASE_URL: String = "https://api.pexels.com/v1/"

    @Provides
    fun provideAuthorizationInterceptor(): AuthInterceptor {
        return AuthInterceptor(
            key = BuildConfig.API_KEY
        )
    }

    @Provides
    fun provideLogInterceptor(): HttpLoggingInterceptor{
        return HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    fun provideOkHttpClient(
        authorizationInterceptor: AuthInterceptor,
        logInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authorizationInterceptor)
            .addInterceptor(logInterceptor)
            .readTimeout(TIME_OUT_SECONDS, TimeUnit.SECONDS)
            .connectTimeout(TIME_OUT_SECONDS, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()
    }

    @Provides
    fun provideApi(
        retrofit: Retrofit
    ): PictureRepository {
        return retrofit.create(PictureRepository::class.java)
    }
}