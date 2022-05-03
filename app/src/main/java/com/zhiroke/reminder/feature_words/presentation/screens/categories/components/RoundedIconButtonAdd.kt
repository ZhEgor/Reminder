package com.zhiroke.reminder.feature_words.presentation.screens.categories.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RoundedIconButtonAdd(modifier: Modifier = Modifier, onClick: () -> Unit = { }) {
    Button(
        modifier = modifier,
        onClick = onClick,
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondaryVariant)
    ) {
        Icon(
            contentDescription = "add word",
            imageVector = Icons.Default.Add,
            modifier = Modifier.size(32.dp),
            tint = MaterialTheme.colors.background
        )
    }
}