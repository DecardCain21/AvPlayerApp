package com.example.avplayerapp.app

import android.app.Application
import com.example.avplayerapp.main.di.mainModule
import com.example.core.network.di.appModule
import com.example.core.network.di.networkModule
import com.example.player.di.avPlayerNotificationModule
import com.example.player.di.playbackModule
import com.example.player.di.searchTracksModule
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
                avPlayerNotificationModule(this@AvitoPlayerApp),
                appModule(this@AvitoPlayerApp)
            )

        }
    }
}