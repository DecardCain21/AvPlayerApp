package com.example.avplayerapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.avplayerapp.ui.theme.AvPlayerAppTheme
import com.example.searchtracks.domain.api.GetDeezerTracksUseCase
import com.example.core.domain.models.Track
import com.example.searchtracks.domain.api.GetDeezerChartUseCase
import org.koin.android.ext.android.inject

public class MainActivity : ComponentActivity() {

    private val getDeezerTracksUseCase: GetDeezerTracksUseCase by inject()
    private val getDeezerChartUseCase: GetDeezerChartUseCase by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AvPlayerAppTheme {
                val tracksState = remember { mutableStateOf<Result<List<Track>>?>(null) }
                LaunchedEffect(Unit) {
                    /*tracksState.value = getDeezerTracksUseCase("Mick Gordon")*/
                    tracksState.value = getDeezerChartUseCase()
                }
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    /*Greeting(
                        name = "$getDeezerTracksUseCase",
                        modifier = Modifier.padding(innerPadding)
                    )*/
                    /*when (val result = tracksState.value) {
                        is Result.Success -> TrackList(
                            tracks = result.data,
                            modifier = Modifier.padding(innerPadding)
                        )
                        is Result.Failure -> ErrorMessage(error = result.exception.message)
                        null -> LoadingIndicator()
                    }*/
                    tracksState.value?.getOrNull()
                        ?.let { TrackList(it, modifier = Modifier.padding(innerPadding)) }
                }
            }
        }
    }
}

@Composable
private fun TrackList(tracks: List<Track>, modifier: Modifier = Modifier) {
    LazyColumn(modifier) {
        items(tracks) { track ->
            Text(text = track.title)
            // Другая информация о треке
        }
    }
}

@Composable
private fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun GreetingPreview() {
    AvPlayerAppTheme {
        Greeting("Android")
    }
}