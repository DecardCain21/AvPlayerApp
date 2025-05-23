package com.example.player.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage


@Composable
public fun AvPlayerTrackItem(
    titleName: String,
    authorName: String,
    coverUrl: String,
    onClick: () -> Unit = {}
) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = Color.Green,
        modifier = Modifier
            .wrapContentSize()
            .clickable {
                onClick()
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Gray)
        ) {
            AsyncImage(
                model = coverUrl,
                contentDescription = "Track cover",
                modifier = Modifier.wrapContentSize(),
                contentScale = ContentScale.Fit,
                placeholder = painterResource(com.example.core.R.drawable.ic_launcher_foreground)
            )
            Column(modifier = Modifier.padding(start = 8.dp)) {
                Text(text = titleName, color = Color.White)
                Text(text = authorName, color = Color.White)
            }

        }
    }

}