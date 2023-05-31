package com.drahovac.nbaplayers.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.drahovac.nbaplayers.ui.theme.NBAPlayersTheme

/**
 * Displays circular progress indicator in the middle of the box.
 */
@Composable
fun BoxScope.LoadingProgress() {
    CircularProgressIndicator(
        modifier = Modifier
            .width(24.dp)
            .align(
                Alignment.Center
            )
    )
}

@Preview
@Composable
fun LoadingProgressPreview() {
    NBAPlayersTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            LoadingProgress()
        }
    }
}
