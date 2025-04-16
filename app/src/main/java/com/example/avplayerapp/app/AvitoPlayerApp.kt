package com.example.avplayerapp.app

import android.app.Application
import com.example.avplayerapp.main.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

public class AvitoPlayerApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AvitoPlayerApp)
            mainModule
        }
    }
}