package com.zhiroke.reminder.feature_words.presentation.screens.createWord.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zhiroke.reminder.R
import com.zhiroke.reminder.ui.theme.Black40

@Composable
fun SelectCategoryField(text: String?, onClick: () -> Unit) {
    Column(
        modifier = Modifier.clickable {
            onClick()
        }
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 16.dp, end = 16.dp),
            text = stringResource(R.string.category),
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp),
            text = text ?: stringResource(R.string.tap_to_select_category),
            style = TextStyle(fontSize = 18.sp, color = Black40),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Divider(
            color = MaterialTheme.colors.onSurface,
            thickness = 1.dp,
        )
    }
}

@Preview
@Composable
fun PreviewSelectCategoryField() {
    SelectCategoryField(text = null) {

    }
}