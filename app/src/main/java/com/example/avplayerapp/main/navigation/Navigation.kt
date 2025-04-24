package com.example.avplayerapp.main.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentPasteSearch
import androidx.compose.material.icons.filled.Download
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.player.ui.downloaded.navigation.DOWNLOADED_SCREEN_ROUTE
import com.example.player.ui.downloaded.navigation.downloadedScreen
import com.example.player.ui.playerscreen.navigation.PLAYER_SCREEN_ROUTE
import com.example.player.ui.playerscreen.navigation.navigateToPlayerScreen
import com.example.player.ui.playerscreen.navigation.playerScreen
import com.example.player.ui.searchscreen.navigation.SEARCH_SCREEN_ROUTE
import com.example.player.ui.searchscreen.navigation.searchTracksScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
public fun Navigation() {
    val navController: NavHostController = rememberNavController()
    val noBottomBarScreens = listOf(PLAYER_SCREEN_ROUTE)

    Scaffold(
        bottomBar = {
            val currentBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = currentBackStackEntry?.destination?.route

            if (currentRoute !in noBottomBarScreens) {
                NavigationBar(
                    modifier = Modifier
                        .wrapContentSize()
                        .drawWithCache {
                            val strokeWidth = 1.dp.toPx()
                            onDrawWithContent {
                                drawContent()
                                drawLine(
                                    color = Color.Yellow,
                                    start = Offset(0f, 0f),
                                    end = Offset(size.width, 0f),
                                    strokeWidth = strokeWidth
                                )
                            }
                        },
                    containerColor = Color.DarkGray,
                ) {
                    val items = listOf(
                        BottomNavigationItem(
                            title = "Downloaded",
                            icon = Icons.Default.Download,
                            route = DOWNLOADED_SCREEN_ROUTE,
                            titleColor = Color.Yellow
                        ),
                        BottomNavigationItem(
                            title = "Search Tracks",
                            icon = Icons.Default.ContentPasteSearch,
                            route = SEARCH_SCREEN_ROUTE,
                            titleColor = Color.Yellow
                        ),
                    )

                    items.forEach { item ->
                        NavigationBarItem(
                            modifier = Modifier,
                            selected = currentRoute == item.route,
                            label = {
                                Text(
                                    text = item.title,
                                    style = MaterialTheme.typography.labelMedium,
                                )
                            },
                            onClick = {
                                if (currentRoute != item.route) {
                                    navController.navigate(item.route) {
                                        popUpTo(navController.graph.startDestinationId) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            },
                            colors = NavigationBarItemDefaults.colors(
                                indicatorColor = Color.White,
                                disabledTextColor = Color.Red,
                                selectedTextColor = Color.Yellow,
                                selectedIconColor = Color.DarkGray,
                                unselectedTextColor = Color.LightGray,
                                unselectedIconColor = Color.LightGray
                            ),
                            alwaysShowLabel = true,
                            icon = {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.route
                                )
                            }
                        )
                    }
                }
            }
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = SEARCH_SCREEN_ROUTE,
            modifier = Modifier
        ) {
            searchTracksScreen { navController.navigateToPlayerScreen(it) }
            downloadedScreen()
            playerScreen()
        }
    }

}

public data class BottomNavigationItem(
    val title: String,
    val icon: ImageVector,
    val route: String,
    val titleColor: Color = Color.White
)