package com.zhiroke.reminder.feature_words.presentation.screens.createWord.components

import androidx.compose.animation.core.animateOffset
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.ContentPaste
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
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
    hasError: Boolean = false,
    onValueChange: (String) -> Unit
) {
    val clipboardManager: ClipboardManager = LocalClipboardManager.current
    var isFocused by remember { mutableStateOf(false) }
    var labelPosition by remember {
        if (value.isEmpty()) mutableStateOf(LabelPosition.HintPosition)
        else mutableStateOf(LabelPosition.LabelPosition)
    }
    val transition = updateTransition(targetState = labelPosition, label = "label transition")
    val labelOffset by transition.animateOffset(label = "Label transition") { position ->
        when (position) {
            LabelPosition.LabelPosition -> Offset(0F, 0F)
            LabelPosition.HintPosition -> Offset(0F, 36F)
        }
    }
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .offset(x = labelOffset.x.dp, y = labelOffset.y.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = title,
                    fontWeight = if (labelPosition == LabelPosition.HintPosition) FontWeight.Normal else FontWeight.Bold,
                    fontSize = if (labelPosition == LabelPosition.HintPosition) 20.sp else 16.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = if (hasError) MaterialTheme.colors.error else MaterialTheme.colors.onSurface,
                )
            }
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd,
            ) {
                TextButton(
                    onClick = {
                        (clipboardManager.getText()?.text ?: "").let {
                            if (it.isNotEmpty()) labelPosition = LabelPosition.LabelPosition
                            onValueChange(it)
                        }
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
        Row(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                .fillMaxWidth()
                .wrapContentHeight(),

            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                Modifier
                    .weight(1f)
            ) {
                TransparentHintTextField(
                    text = value,
                    textStyle = TextStyle(fontSize = 18.sp),
                    onFocusChange = {
                        isFocused = it.isFocused
                        labelPosition =
                            if (!it.isFocused && value.isEmpty()) LabelPosition.HintPosition
                            else LabelPosition.LabelPosition
                    }
                ) {
                    onValueChange(it)
                }
            }
            Box(
                modifier = Modifier
                    .padding(start = 8.dp, end = 21.dp),
            ) {
                if (value.isNotEmpty()) {
                    Icon(
                        modifier = Modifier
                            .size(20.dp)
                            .clip(CircleShape)
                            .clickable {
                                onValueChange("")
                                labelPosition = if (isFocused) LabelPosition.LabelPosition
                                else LabelPosition.HintPosition
                            },
                        imageVector = Icons.Filled.Cancel,
                        contentDescription = "remove text",
                        tint = if (hasError) MaterialTheme.colors.error else MaterialTheme.colors.onSurface
                    )
                }
            }
        }
        Divider(
            color = if (hasError) MaterialTheme.colors.error else MaterialTheme.colors.onSurface,
            thickness = 1.dp,
        )
    }
}

private enum class LabelPosition {
    HintPosition,
    LabelPosition
}

@Preview
@Composable
fun PreviewCreateWordField() {
    CreateWordField(title = "Spelling", value = "test", onValueChange = { })
}