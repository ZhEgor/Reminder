package com.zhiroke.reminder.featurewords.presentation.screens.wordlist.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DeleteOutline
import androidx.compose.material.icons.rounded.DoneOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zhiroke.reminder.featurewords.domain.model.Word

@Composable
fun WordListItemExpanded(
    modifier: Modifier = Modifier,
    word: Word,
    position: Int,
    onSave: () -> Unit,
    onDelete: () -> Unit
) {
    Column(modifier = modifier) {
        Row(modifier = Modifier.padding(4.dp)) {
            Text(
                text = "$position.",
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            OutlinedButton(
                onClick = { onDelete() },
                colors = ButtonDefaults.outlinedButtonColors(
                    backgroundColor = Color.Transparent,
                    contentColor = MaterialTheme.colors.onPrimary
                ),
                border = ButtonDefaults.outlinedBorder.copy(width = 2.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.padding(end = 4.dp),
                        text = "Delete"
                    )
                    Icon(
                        imageVector = Icons.Rounded.DeleteOutline,
                        contentDescription = "delete word"
                    )
                }
            }
            OutlinedButton(
                onClick = { onSave() },
                colors = ButtonDefaults.outlinedButtonColors(
                    backgroundColor = Color.Transparent,
                    contentColor = MaterialTheme.colors.onPrimary
                ),
                border = ButtonDefaults.outlinedBorder.copy(width = 2.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.padding(end = 4.dp),
                        text = "Save"
                    )
                    Icon(imageVector = Icons.Rounded.DoneOutline, contentDescription = "save word")
                }
            }
        }
    }
}


@Preview
@Composable
fun PreviewWordListItemExpanded() {
    WordListItemExpanded(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(4.dp) // margin
            .fillMaxSize()
            .clip(RoundedCornerShape(4.dp))
            .background(MaterialTheme.colors.primary)
            .clickable {},
        word = Word("", "spelling", "translation", "pronunciation", "", "23141234", "", 1, 1),
        position = 1,
        onSave = { /*TODO*/ },
        onDelete = {}
    )
}