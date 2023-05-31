package com.drahovac.nbaplayers.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.drahovac.nbaplayers.viewModel.DetailState

/**
 * Displays a detail screen.
 *
 * @param state The state of the detail screen.
 * @param retry A function that can be called to retry loading the detail.
 * @param successContent A function that can be used to render the content of the detail screen
 * when it is loaded successfully.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> DetailScreen(
    state: DetailState<T>,
    title: String,
    retry: () -> Unit,
    onBack: () -> Unit,
    successContent: @Composable (DetailState.Success<T>) -> Unit,
) {
    Scaffold(topBar = { AppBar(title = title, onBack = onBack) }) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            when (state) {
                is DetailState.Error -> ErrorView(error = state.error, retry)
                DetailState.LOADING -> LoadingProgress()
                is DetailState.Success -> successContent(state)
            }
        }
    }
}