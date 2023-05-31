package com.drahovac.nbaplayers.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.drahovac.nbaplayers.R
import com.drahovac.nbaplayers.domain.TeamDetail
import com.drahovac.nbaplayers.ui.DetailScreen
import com.drahovac.nbaplayers.ui.TextBody
import com.drahovac.nbaplayers.ui.TextHeadline
import com.drahovac.nbaplayers.viewModel.DetailState
import com.drahovac.nbaplayers.viewModel.TeamDetailViewModel
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

/**
 * Displays the team detail screen for a given team ID.
 *
 * @param id The team ID.
 * @param viewModel The view model for the team detail screen.
 */
@Composable
fun TeamDetailScreen(
    id: String,
    navController: NavController,
    viewModel: TeamDetailViewModel = getViewModel { parametersOf(id) }
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    TeamScreenContent(state, viewModel::retry, navController::navigateUp)
}

@Composable
private fun TeamScreenContent(
    state: DetailState<TeamDetail>,
    retry: () -> Unit,
    onBack: () -> Unit,
) {
    DetailScreen(
        state = state, retry = retry,
        title = stringResource(id = R.string.team_detail),
        onBack = onBack
    ) {
        TeamDetailContent(it)
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun TeamDetailContent(state: DetailState.Success<TeamDetail>) {
    val detail = state.detail
    Column(
        Modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.Top) {
            GlideImage(
                model = state.imageUrl,
                contentDescription = stringResource(id = R.string.team_image),
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 16.dp),
            )
            Column(modifier = Modifier.weight(3f)) {
                TextHeadline(text = detail.name)
                TextBody(text = "${stringResource(R.string.team_full_name)} ${detail.fullName}")
                TextBody(text = "${stringResource(R.string.team_abbrevation)} ${detail.abbreviation}")
                TextBody(text = "${stringResource(R.string.team_division)} ${detail.division}")
                TextBody(text = "${stringResource(R.string.team_city)} ${detail.city}")
                TextBody(text = "${stringResource(R.string.team_conference)} ${detail.conference}")
            }
        }
    }
}
