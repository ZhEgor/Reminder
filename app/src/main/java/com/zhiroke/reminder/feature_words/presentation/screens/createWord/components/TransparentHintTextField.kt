package com.zhiroke.reminder.feature_words.presentation.screens.createWord.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.zhiroke.reminder.ui.theme.Black30
import com.zhiroke.reminder.ui.theme.Black40

@Composable
fun TransparentHintTextField(
    text: String,
    hint: String,
    modifier: Modifier = Modifier,
    isHintVisible: Boolean = true,
    textStyle: TextStyle = TextStyle(),
    singleLine: Boolean = false,
    onFocusChange: (FocusState) -> Unit = {},
    onValueChange: (String) -> Unit
) {
    Box(modifier = modifier) {
        BasicTextField(
            value = text,
            onValueChange = onValueChange,
            singleLine = singleLine,
            textStyle = textStyle,
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged {
                    onFocusChange(it)
                }
        )
        if (text.isEmpty() && isHintVisible) {
            Text(
                text = hint,
                style = textStyle,
                color = Black40
            )
        }
    }
}