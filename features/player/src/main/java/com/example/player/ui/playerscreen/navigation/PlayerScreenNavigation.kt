package com.example.player.ui.playerscreen.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.player.ui.playerscreen.PlayerScreen
import com.example.player.ui.searchscreen.navigation.SEARCH_SCREEN_ROUTE

public const val PLAYER_SCREEN_ROUTE: String = "playerscreen"

public fun NavController.navigateToPlayerScreen(trackId: Long): Unit =
    navigate(route = "$PLAYER_SCREEN_ROUTE/$trackId") {
        launchSingleTop
    }

public fun NavGraphBuilder.playerScreen() {
    composable(
        route = "$PLAYER_SCREEN_ROUTE/{$SEARCH_SCREEN_ROUTE}",
        arguments = listOf(navArgument(name = SEARCH_SCREEN_ROUTE) { type = NavType.LongType })
    ) { backStackEntry ->
        val id = backStackEntry.arguments?.getLong(SEARCH_SCREEN_ROUTE)
        PlayerScreen(trackId = id ?: 0L)
    }
}