package com.zhiroke.reminder.feature_words.presentation.screens.createWord.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Dash(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.clip(RoundedCornerShape(5.dp)),
    ) {
        Divider(
            color = MaterialTheme.colors.primary,
            thickness = 7.dp,
        )
    }
}

@Preview
@Composable
fun PreviewDash() {
    Dash(
        modifier = Modifier
            .width(64.dp)
            .padding(16.dp)
    )
}