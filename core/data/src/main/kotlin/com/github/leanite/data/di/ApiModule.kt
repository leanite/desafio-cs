package com.github.leanite.data.di

import com.github.leanite.data.api.RepositoryApi
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    single { provideRepositoryApi(get()) }
}

private fun provideRepositoryApi(retrofit: Retrofit): RepositoryApi =
    retrofit.create(RepositoryApi::class.java)
