package com.example.exo_player.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.exo_player.screens.Home
import com.example.exo_player.screens.OnlinePlayer
import com.example.exo_player.screens.RawPlayer
import com.example.exo_player.screens.YoutubePlayer

@Composable
fun NavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = NavStarter
    ) {
        navigation<NavStarter>(
            startDestination = HomeScreen
        ){
            composable<HomeScreen> {
                Home(
                    onOnlineClick = { navController.navigate(OnlinePlayerScreen) },
                    onRawClick = { navController.navigate(RawScreen) },
                    onYoutubeClick = { navController.navigate(YoutubePlayerScreen) }
                )
            }

            composable<OnlinePlayerScreen>{
                OnlinePlayer()
            }

            composable<RawScreen> {
                RawPlayer()
            }

            composable<YoutubePlayerScreen> {
                YoutubePlayer(videoId = "JtaRoPZ3Avs")
            }
        }
    }
}