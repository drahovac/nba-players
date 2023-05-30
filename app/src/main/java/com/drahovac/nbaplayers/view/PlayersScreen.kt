package com.drahovac.nbaplayers.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.drahovac.nbaplayers.R
import com.drahovac.nbaplayers.domain.Player
import com.drahovac.nbaplayers.domain.Team
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
 */
@Composable
fun PlayersScreen(viewModel: PlayersViewModel = getViewModel()) {

    Content(viewModel.players)
}

@Composable
private fun Content(playersFlow: Flow<PagingData<Player>>) {
    val players = playersFlow.collectAsLazyPagingItems()

    Box {
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
                players[index]?.let { PlayerItem(it) }
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
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp, horizontal = 8.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(MaterialTheme.colorScheme.error)
                    .align(Alignment.TopCenter)
            ) {
                TextBody(
                    modifier = Modifier.padding(8.dp),
                    color = MaterialTheme.colorScheme.onError,
                    text = "Error loading players: ${state.error}."
                )

                Button(
                    onClick = retry, modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.End)
                ) {
                    TextBody(text = stringResource(R.string.retry))
                }
            }
        }

        LoadState.Loading -> CircularProgressIndicator(
            modifier = Modifier
                .width(24.dp)
                .align(
                    Alignment.Center
                )
        )

        is LoadState.NotLoading -> { // nothing to show
        }
    }
}

@Composable
fun PlayerItem(player: Player) {
    Card(
        Modifier
            .padding(horizontal = 8.dp)
            .padding(bottom = 8.dp)
    ) {
        Column(
            Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            TextBody(text = "${player.name} ${player.lastName}")
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
                            "Name",
                            "Surname",
                            "F",
                            Team("", "Team name")
                        ),
                        Player(
                            "Name 2",
                            "Surname",
                            "CF",
                            Team("", "Team name")
                        ),
                        Player(
                            "Name 3",
                            "Surname",
                            "B",
                            Team("", "Team name")
                        )
                    )
                )
            )
        )
    }
}
