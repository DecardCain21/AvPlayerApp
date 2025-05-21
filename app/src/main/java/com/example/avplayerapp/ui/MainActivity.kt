package com.example.avplayerapp.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.OptIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import androidx.media3.common.util.UnstableApi
import com.example.avplayerapp.main.navigation.Navigation
import com.example.avplayerapp.ui.theme.AvPlayerAppTheme
import com.example.player.ui.playernot.api.AvServiceProvider
import com.example.player.ui.playernot.service.AvMediaService
import com.example.player.ui.playernot.service.AvMediaServiceHandler
import com.example.player.ui.playernot.service.PlayerEvent
import org.koin.android.ext.android.inject

public class MainActivity : ComponentActivity(), AvServiceProvider {
    private var isServiceRunning = false
    private val avMediaServiceHandler: AvMediaServiceHandler by inject()

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AvPlayerAppTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .statusBarsPadding()
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    Navigation()
                    //TestNotificationButton(avMediaServiceHandler = avMediaServiceHandler)
                }
            }
        }
    }

    override fun onStop() {
        super.onStop()
        startService()
    }

    @OptIn(UnstableApi::class)
    public override fun startService() {
        if (!isServiceRunning) {
            val intent = Intent(this, AvMediaService::class.java)
            startForegroundService(intent)
            isServiceRunning = true
        }
    }

    @Composable
    public fun TestNotificationButton(avMediaServiceHandler: AvMediaServiceHandler) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    avMediaServiceHandler.addMediaItem(
                        MediaItem.Builder()
                            .setUri("https://cdnt-preview.dzcdn.net/api/1/1/3/b/5/0/3b5a5013027d0eb81daec7412a9a0aec.mp3?hdnea=exp=1747320838~acl=/api/1/1/3/b/5/0/3b5a5013027d0eb81daec7412a9a0aec.mp3*~data=user_id=0,application_id=42~hmac=1f23ad9c9f694b9457d00cd0d511afaa62d3b1b56a2c1204d52868ef59e37a91")
                            .setMediaMetadata(
                                MediaMetadata.Builder()
                                    .setAlbumArtist("audio.artist")
                                    .setDisplayTitle("audio.title")
                                    .setSubtitle("audio.displayName")
                                    .build()
                            )
                            .build()
                    )
                    startService()
                }
            ) {
                Text("Показать уведомление")
            }
        }
    }
}