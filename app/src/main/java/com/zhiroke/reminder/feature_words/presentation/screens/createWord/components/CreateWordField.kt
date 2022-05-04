package com.zhiroke.reminder.feature_words.presentation.screens.createWord.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentPaste
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zhiroke.reminder.R

@Composable
fun CreateWordField(
    value: String,
    title: String,
    hint: String = title,
    onValueChange: (String) -> Unit
) {
    val clipboardManager: ClipboardManager = LocalClipboardManager.current
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd,
            ) {
                TextButton(
                    modifier = Modifier
                        .padding(0.dp)
                        .padding(0.dp),
                    onClick = {
                        onValueChange(clipboardManager.getText()?.text ?: "")
                    },
                    shape = CircleShape
                ) {
                    Icon(
                        modifier = Modifier.size(20.dp),
                        contentDescription = stringResource(R.string.paste_from_clipboard),
                        imageVector = Icons.Default.ContentPaste,
                        tint = MaterialTheme.colors.onSurface
                    )
                }
            }
        }
        TransparentHintTextField(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
            text = value,
            hint = hint,
            textStyle = TextStyle(fontSize = 18.sp)
        ) {
            onValueChange(it)
        }
        Divider(
            color = MaterialTheme.colors.onSurface,
            thickness = 1.dp,
        )
    }
}

@Preview
@Composable
fun PreviewCreateWordField() {
    CreateWordField(title = "Spelling", value = "smth", onValueChange = { })
}