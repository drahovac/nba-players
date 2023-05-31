package com.drahovac.nbaplayers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.drahovac.nbaplayers.ui.theme.NBAPlayersTheme
import com.drahovac.nbaplayers.view.Destination
import com.drahovac.nbaplayers.view.Destination.Companion.PLAYER_ID_PARAM
import com.drahovac.nbaplayers.view.Destination.Companion.TEAM_ID_PARAM
import com.drahovac.nbaplayers.view.PlayerDetailScreen
import com.drahovac.nbaplayers.view.PlayersScreen
import com.drahovac.nbaplayers.view.TeamDetailScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NBAPlayersTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation()
                }
            }
        }
    }

    @Composable
    private fun Navigation() {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = Destination.PlayersList.route,
        ) {
            composable(Destination.PlayersList.route) {
                PlayersScreen(navController = navController)
            }
            composable(Destination.PlayerDetail().route,
                arguments = listOf(navArgument(PLAYER_ID_PARAM) {
                    nullable = false
                    type = NavType.StringType
                }
                )) {
                PlayerDetailScreen(
                    it.arguments?.getString(PLAYER_ID_PARAM) ?: "",
                    navController = navController
                )
            }
            composable(Destination.TeamDetail().route,
                arguments = listOf(navArgument(TEAM_ID_PARAM) {
                    nullable = false
                    type = NavType.StringType
                }
                )) {
                TeamDetailScreen(it.arguments?.getString(TEAM_ID_PARAM) ?: "")
            }
        }
    }
}
