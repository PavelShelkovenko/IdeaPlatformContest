package com.pavelshelkovenko.ideaplatformcontest

import android.app.Application
import com.pavelshelkovenko.ideaplatformcontest.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                appModule,
            )
        }
    }
}