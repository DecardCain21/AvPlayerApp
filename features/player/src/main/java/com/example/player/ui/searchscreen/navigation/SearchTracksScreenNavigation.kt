package com.example.player.ui.searchscreen.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.player.domain.models.Track
import com.example.player.ui.searchscreen.SearchTracksScreen

public const val SEARCH_SCREEN_ROUTE: String = "searchscreen"

public fun NavController.navigateToSearchTracksScreen(): Unit =
    navigate(SEARCH_SCREEN_ROUTE) {
        popUpTo(SEARCH_SCREEN_ROUTE) {
            inclusive
        }
    }

public fun NavGraphBuilder.searchTracksScreen() {
    composable(route = SEARCH_SCREEN_ROUTE) {
        SearchTracksScreen()
    }
}