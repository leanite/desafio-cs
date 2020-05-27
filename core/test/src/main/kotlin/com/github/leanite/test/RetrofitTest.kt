package com.github.leanite.test

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitTest {
    private const val CONNECT_TIME_OUT: Long = 200
    private const val READ_TIME_OUT: Long = 30
    private const val WRITE_TIME_OUT: Long = 40
    private const val BASE_URL = "http://localhost:8080/"

    fun <T> provideApi(
        clazz: Class<T>,
        baseUrl: String = BASE_URL): T {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(provideGson()))
            .client(provideOkHttpClient())
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CustomCoroutineCallAdapterAdapterFactory())
            .build()
        return retrofit.create(clazz)
    }

    private fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()

        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        gsonBuilder.setLenient()

        return gsonBuilder.create()
    }

    private fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)
            .build()
    }
}