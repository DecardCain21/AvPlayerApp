package com.example.avplayerapp.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.media3.common.util.UnstableApi
import com.example.avplayerapp.ui.theme.AvPlayerAppTheme
import com.example.player.ui.playernot.service.AvMediaService

public class MainActivity : ComponentActivity() {
    private var isServiceRunning = false

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
                    //TestNotification(startService = { startService() })
                    //Navigation()
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(
                            onClick = {
                                // Создаем уведомление
                                /*val builder = NotificationCompat.Builder(context, "default_channel")
                                    .setSmallIcon(android.R.drawable.ic_dialog_info)
                                    .setContentTitle("Мое уведомление")
                                    .setContentText("Привет, это тестовое уведомление!")
                                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)

                                // Показываем уведомление
                                with(NotificationManagerCompat.from(context)) {
                                    notify(notificationId, builder.build())
                                    notificationId++ // Увеличиваем ID для следующего уведомления
                                }*/
                                startService()
                            }
                        ) {
                            Text("Показать уведомление")
                        }
                    }
                }
            }
        }
    }

    @OptIn(UnstableApi::class)
    private fun startService() {
        if (!isServiceRunning) {
            val intent = Intent(this, AvMediaService::class.java)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(intent)
            } else {
                startService(intent)
            }
            isServiceRunning = true
        }
    }

}

@SuppressLint("MissingPermission")
@Composable
public fun TestNotification(startService: () -> Unit) {
    val context = LocalContext.current
    var notificationId by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                // Создаем уведомление
                /*val builder = NotificationCompat.Builder(context, "default_channel")
                    .setSmallIcon(android.R.drawable.ic_dialog_info)
                    .setContentTitle("Мое уведомление")
                    .setContentText("Привет, это тестовое уведомление!")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)

                // Показываем уведомление
                with(NotificationManagerCompat.from(context)) {
                    notify(notificationId, builder.build())
                    notificationId++ // Увеличиваем ID для следующего уведомления
                }*/
                startService()
            }
        ) {
            Text("Показать уведомление")
        }
    }
}