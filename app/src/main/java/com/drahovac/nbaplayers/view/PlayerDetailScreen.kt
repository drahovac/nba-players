package com.drahovac.nbaplayers.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.drahovac.nbaplayers.R
import com.drahovac.nbaplayers.ui.ErrorView
import com.drahovac.nbaplayers.ui.LoadingProgress
import com.drahovac.nbaplayers.ui.TextBody
import com.drahovac.nbaplayers.ui.TextHeadline
import com.drahovac.nbaplayers.viewModel.PlayerDetailState
import com.drahovac.nbaplayers.viewModel.PlayerDetailViewModel
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

/**
 * A screen that displays details about player.
 *
 * @param id unique player id to fetch data
 * @param navController The navigation controller to navigate to other screen.
 * @param viewModel The view model that provides the player data.
 */
@Composable
fun PlayerDetailScreen(
    id: String,
    navController: NavController,
    viewModel: PlayerDetailViewModel = getViewModel { parametersOf(id) },
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ScreenContent(state, viewModel::retry) {
        navController.navigate(Destination.TeamDetail(it).route) {
            launchSingleTop = true
        }
    }
}

@Composable
private fun ScreenContent(
    state: PlayerDetailState,
    retry: () -> Unit,
    onTeamClicked: (String) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        when (state) {
            is PlayerDetailState.Error -> ErrorView(error = state.error, retry)
            PlayerDetailState.LOADING -> LoadingProgress()
            is PlayerDetailState.Success -> PlayerDetailContent(state) {
                onTeamClicked(state.detail.team.id)
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PlayerDetailContent(state: PlayerDetailState.Success, onTeamClicked: () -> Unit) {
    val detail = state.detail

    Column(
        Modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.Top) {
            GlideImage(
                model = state.imageUrl,
                contentDescription = stringResource(id = R.string.player_image),
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 16.dp),
            )
            Column(modifier = Modifier.weight(3f)) {
                TextHeadline(text = detail.run { "$firstName $lastName" })
                TextBody(text = "${stringResource(R.string.position)} ${detail.position}")
                TextBody(text = "${stringResource(R.string.height_feet)} ${detail.heightFeet.orUnknown()}")
                TextBody(text = "${stringResource(R.string.height_inches)} ${detail.heightInches.orUnknown()}")
                TextBody(text = "${stringResource(R.string.weight)} ${detail.weightPounds.orUnknown()}")

                Row(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .clickable { onTeamClicked() },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.padding(end = 8.dp),
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        text = "${stringResource(R.string.team)} ${detail.team.name}"
                    )
                    Image(
                        painter = painterResource(id = R.drawable.baseline_arrow_forward),
                        contentDescription = stringResource(id = R.string.team_details)
                    )
                }
            }
        }
    }
}

@Composable
private fun Int?.orUnknown(): String = this?.toString() ?: stringResource(id = R.string.unknown)

