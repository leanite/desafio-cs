package com.github.leanite.desafio.features.repositories

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.KoinComponent
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

val repositoryModule = module {
    viewModel { RepositoryViewModel(get(), get()) }
}

object RepositoryModule: KoinComponent {
    fun inject() = loadKoinModules(repositoryModule)
}
