package com.github.leanite.desafio

import android.app.Application
import com.github.leanite.data.di.apiModule
import com.github.leanite.data.di.interactionModule
import com.github.leanite.data.di.retrofitModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class DesafioApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@DesafioApplication)
            modules(listOf(retrofitModule, apiModule, interactionModule))
        }
    }
}