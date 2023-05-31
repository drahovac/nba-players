package com.drahovac.nbaplayers

import android.app.Application
import com.drahovac.nbaplayers.di.appModule
import org.koin.core.context.GlobalContext.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(appModule)
        }
    }
}