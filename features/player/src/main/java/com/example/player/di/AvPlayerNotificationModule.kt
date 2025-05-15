package com.example.player.di

import android.app.Application
import androidx.annotation.OptIn
import androidx.media3.common.AudioAttributes
import androidx.media3.common.C
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector
import androidx.media3.session.MediaSession
import com.example.player.ui.playernot.notification.AvPlayerNotificationManager
import com.example.player.ui.playernot.service.AvMediaServiceHandler
import org.koin.core.module.Module
import org.koin.dsl.module

@OptIn(UnstableApi::class)
public fun avPlayerNotificationModule(application: Application): Module = module {
    // Общие зависимости
    val context = application.applicationContext

    // AudioAttributes (Singleton)
    single<AudioAttributes> {
        AudioAttributes.Builder()
            .setContentType(C.AUDIO_CONTENT_TYPE_MOVIE)
            .setUsage(C.USAGE_MEDIA)
            .build()
    }

    // ExoPlayer (Singleton)
    single<ExoPlayer> {
        ExoPlayer.Builder(context)
            .setAudioAttributes(get(), true)
            .setHandleAudioBecomingNoisy(true)
            .setTrackSelector(DefaultTrackSelector(context))
            .build()
    }

    // MediaSession (Singleton)
    single<MediaSession> {
        MediaSession.Builder(context, get<ExoPlayer>()).build()
    }

    // NotificationManager (Singleton)
    single<AvPlayerNotificationManager> {
        AvPlayerNotificationManager(
            context = context,
            exoPlayer = get<ExoPlayer>()
        )
    }

    // ServiceHandler (Singleton)
    single {
        AvMediaServiceHandler(get<ExoPlayer>())
    }
}