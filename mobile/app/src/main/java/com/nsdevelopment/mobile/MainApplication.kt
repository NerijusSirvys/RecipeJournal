package com.nsdevelopment.mobile

import android.app.Application
import com.nsdevelopment.mobile.screens.home.HomeScreenViewmodel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module


class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(appModule)
        }
    }
}


val appModule = module {
    viewModelOf(::HomeScreenViewmodel)
}