package com.example.player.ui.downloaded.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.player.ui.downloaded.DownloadedScreen

public const val DOWNLOADED_SCREEN_ROUTE: String = "downloadedscreen"

public fun NavController.navigateToDownloadedScreen(): Unit =
    navigate(DOWNLOADED_SCREEN_ROUTE) {
        popUpTo(DOWNLOADED_SCREEN_ROUTE) {
            inclusive
        }
    }

public fun NavGraphBuilder.downloadedScreen() {
    composable(route = DOWNLOADED_SCREEN_ROUTE) {
        DownloadedScreen()
    }
}