package com.drahovac.nbaplayers.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.drahovac.nbaplayers.R
import com.drahovac.nbaplayers.ui.theme.NBAPlayersTheme

@Composable
fun AppBar(
    title: String,
    onBack: () -> Unit,
) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(8.dp)) {
        Image(
            painter = painterResource(id = R.drawable.baseline_arrow_back),
            contentDescription = stringResource(id = R.string.back),
            modifier = Modifier.clickable { onBack() }
        )

        TextHeadline(text = title, modifier = Modifier.padding(start = 16.dp))
    }
}

@Preview
@Composable
fun AppBarPreview() {
    NBAPlayersTheme {
        AppBar("Title") {}
    }
}
