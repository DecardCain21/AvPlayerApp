package com.example.avplayerapp.app

import android.app.Application
import com.example.avplayerapp.main.di.mainModule
import com.example.core.network.di.appModule
import com.example.core.network.di.networkModule
import com.example.playback.di.playbackModule
import com.example.searchtracks.di.searchTracksModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

public class AvitoPlayerApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AvitoPlayerApp)
            modules(
                mainModule,
                searchTracksModule,
                networkModule,
                playbackModule,
                appModule(this@AvitoPlayerApp)
            )

        }
    }
}