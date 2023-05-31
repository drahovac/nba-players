package com.drahovac.nbaplayers.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
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
@Composable
fun <T> DetailScreen(
    state: DetailState<T>,
    retry: () -> Unit,
    successContent: @Composable (DetailState.Success<T>) -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        when (state) {
            is DetailState.Error -> ErrorView(error = state.error, retry)
            DetailState.LOADING -> LoadingProgress()
            is DetailState.Success -> successContent(state)
        }
    }
}