package com.github.leanite.data.di

import android.app.Application
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val retrofitModule = module {
    single { provideOkHttpClient(androidApplication()) }
    single { provideRetrofit(get()) }
}

private const val BASE_URL = "https://api.github.com/"
private const val CACHE_SIZE_10_MB: Long = 10 * 1024 * 1024

private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

private fun provideOkHttpClient(application: Application): OkHttpClient =
    OkHttpClient.Builder()
        .readTimeout(4, TimeUnit.SECONDS)
        .cache(Cache(application.cacheDir, CACHE_SIZE_10_MB))
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()