package com.drahovac.nbaplayers.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.drahovac.nbaplayers.ui.theme.NBAPlayersTheme

@Composable
fun TextBody(
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    text: String
) {
    Text(modifier = modifier, text = text, style = MaterialTheme.typography.bodyMedium)
}

@Composable
fun TextHeadline(modifier: Modifier = Modifier, text: String) {
    Text(modifier = modifier, text = text, style = MaterialTheme.typography.headlineSmall)
}

@Preview
@Composable
fun TextPreview() {
    NBAPlayersTheme {
        Column {
            TextHeadline(text = "Headline")
            TextBody(text = "Body text")
        }
    }
}