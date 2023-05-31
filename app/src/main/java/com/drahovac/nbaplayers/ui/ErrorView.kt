package com.drahovac.nbaplayers.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.drahovac.nbaplayers.R
import com.drahovac.nbaplayers.ui.theme.NBAPlayersTheme


/**
 * Displays an error message and a button to retry loading the data.
 *
 * @param error The error that occurred.
 * @param retry The function to call to retry loading the players.
 */
@Composable
fun BoxScope.ErrorView(error: Throwable, retry: () -> Unit) {
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
            text = "${stringResource(id = R.string.error_loading)} ${error}."
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

@Preview
@Composable
fun ErrorViewPreview() {
    NBAPlayersTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            ErrorView(error = Throwable("Error message")) {
            }
        }
    }
}
