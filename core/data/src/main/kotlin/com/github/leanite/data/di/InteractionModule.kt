package com.github.leanite.data.di

import com.github.leanite.data.api.RepositoryApi
import com.github.leanite.data.interaction.GetRepositoriesInteractor
import org.koin.dsl.module

val interactionModule = module {
    factory { provideGetRepositoriesInteractor(get()) }
}

private fun provideGetRepositoriesInteractor(api: RepositoryApi): GetRepositoriesInteractor =
    GetRepositoriesInteractor(api)