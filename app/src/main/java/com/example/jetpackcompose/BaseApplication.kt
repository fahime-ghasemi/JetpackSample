package com.example.jetpackcompose

import android.app.Application
import com.example.jetpackcompose.di.Modules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BaseApplication)
            modules(Modules.appModule)
        }
    }
}