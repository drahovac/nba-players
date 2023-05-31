package com.drahovac.nbaplayers.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.drahovac.nbaplayers.R
import com.drahovac.nbaplayers.domain.Player
import com.drahovac.nbaplayers.domain.Team
import com.drahovac.nbaplayers.ui.ErrorView
import com.drahovac.nbaplayers.ui.LoadingProgress
import com.drahovac.nbaplayers.ui.TextBody
import com.drahovac.nbaplayers.ui.TextHeadline
import com.drahovac.nbaplayers.ui.theme.NBAPlayersTheme
import com.drahovac.nbaplayers.viewModel.PlayersViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import org.koin.androidx.compose.getViewModel

/**
 * A screen that displays a list of players.
 *
 * @param viewModel The view model that provides the players data.
 * @param navController The navigation controller to navigate to other screen.
 */
@Composable
fun PlayersScreen(viewModel: PlayersViewModel = getViewModel(), navController: NavController) {

    Content(viewModel.players) {
        navController.navigate(Destination.PlayerDetail(it).route) {
            launchSingleTop = true
        }
    }
}

@Composable
private fun Content(
    playersFlow: Flow<PagingData<Player>>,
    onItemClicked: (String) -> Unit
) {
    val players = playersFlow.collectAsLazyPagingItems()

    Box(Modifier.fillMaxSize()) {
        LazyColumn {
            item {
                TextHeadline(
                    text = stringResource(R.string.nba_players),
                    modifier = Modifier.padding(vertical = 16.dp, horizontal = 8.dp),
                )
            }

            items(
                count = players.itemCount,
                key = { it.hashCode() }
            ) { index ->
                players[index]?.let { PlayerItem(it, onItemClicked) }
            }
        }
        ScreenStateInfo(players.loadState) {
            players.retry()
        }
    }
}

@Composable
fun BoxScope.ScreenStateInfo(loadState: CombinedLoadStates, retry: () -> Unit) {
    LoadStateInfo(loadState.append, retry)
    LoadStateInfo(loadState.refresh, retry)
}

@Composable
private fun BoxScope.LoadStateInfo(state: LoadState, retry: () -> Unit) {
    when (state) {
        is LoadState.Error -> {
            ErrorView(state.error, retry)
        }

        LoadState.Loading -> {
            LoadingProgress()
        }

        is LoadState.NotLoading -> { // nothing to show
        }
    }
}

@Composable
fun PlayerItem(player: Player, onItemClicked: (String) -> Unit) {
    Card(
        Modifier
            .padding(horizontal = 8.dp)
            .padding(bottom = 8.dp)
            .clickable { onItemClicked(player.id) }
    ) {
        Column(
            Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            TextBody(text = "${player.firstName} ${player.lastName}")
            TextBody(text = "${stringResource(R.string.position)} ${player.position}")
            TextBody(text = "${stringResource(R.string.team)} ${player.team.name}")
        }
    }
}

@Preview
@Composable
fun PlayersScreenPreview() {
    NBAPlayersTheme {
        Content(
            playersFlow = MutableStateFlow(
                PagingData.from(
                    listOf(
                        Player(
                            "id1",
                            "Name",
                            "Surname",
                            "F",
                            Team("", "Team name")
                        ),
                        Player(
                            "id2",
                            "Name 2",
                            "Surname",
                            "CF",
                            Team("", "Team name")
                        ),
                        Player(
                            "id3",
                            "Name 3",
                            "Surname",
                            "B",
                            Team("", "Team name")
                        )
                    )
                )
            ),
            onItemClicked = {}
        )
    }
}
