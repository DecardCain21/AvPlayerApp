package com.example.player.ui.playerscreen.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.player.ui.playerscreen.PlayerScreen

public const val PLAYER_SCREEN_ROUTE: String = "playerscreen"

public fun NavController.navigateToPlayerScreen(): Unit =
    navigate(PLAYER_SCREEN_ROUTE) {
        popUpTo(PLAYER_SCREEN_ROUTE) {
            inclusive
        }
    }

public fun NavGraphBuilder.playerScreen() {
    composable(route = PLAYER_SCREEN_ROUTE) {
        PlayerScreen()
    }
}