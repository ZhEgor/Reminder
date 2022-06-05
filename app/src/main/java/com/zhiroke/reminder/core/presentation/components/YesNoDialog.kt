package com.zhiroke.reminder.core.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.zhiroke.reminder.R

@Composable
fun YesNoDialog(
    title: String,
    yesText: String = stringResource(id = R.string.yes),
    noText: String = stringResource(id = R.string.no),
    onClickYes: () -> Unit,
    onDismiss: () -> Unit,
) {
    Dialog(onDismissRequest = { onDismiss() }) {
        Surface(
            modifier = Modifier
                .width(300.dp)
                .wrapContentHeight(),
            shape = RoundedCornerShape(8.dp),
            color = MaterialTheme.colors.surface
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Divider(
                    modifier = Modifier
                        .width(64.dp)
                        .padding(top = 16.dp)
                        .clip(RoundedCornerShape(4.dp)),
                    thickness = 4.dp,
                    color = MaterialTheme.colors.primaryVariant
                )
                Column(
                    modifier = Modifier.padding(bottom = 16.dp, start = 16.dp, end = 16.dp)
                ) {
                    Text(
                        modifier = Modifier.padding(horizontal = 32.dp, vertical = 16.dp),
                        text = title,
                        color = MaterialTheme.colors.onPrimary,
                        fontSize = 20.sp,
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(top = 4.dp)
                    ) {
                        OutlinedButton(
                            modifier = Modifier
                                .weight(1f)
                                .wrapContentHeight()
                                .padding(horizontal = 4.dp),
                            onClick = { onDismiss() },
                            border = BorderStroke(
                                width = 1.dp,
                                color = MaterialTheme.colors.primaryVariant
                            )
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    modifier = Modifier.padding(end = 4.dp),
                                    imageVector = Icons.Default.Close,
                                    contentDescription = stringResource(R.string.no_description),
                                    tint = MaterialTheme.colors.onPrimary
                                )
                                Text(
                                    text = noText,
                                    color = MaterialTheme.colors.onPrimary
                                )
                            }
                        }
                        Button(
                            modifier = Modifier
                                .weight(1f)
                                .wrapContentHeight()
                                .padding(horizontal = 4.dp),
                            onClick = {
                                onClickYes()
                                onDismiss()
                            },
                            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primaryVariant)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    modifier = Modifier.padding(end = 4.dp),
                                    imageVector = Icons.Default.Done,
                                    contentDescription = stringResource(R.string.yes_description)
                                )
                                Text(text = yesText)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewYesNoDialog() {
    YesNoDialog(
        title = stringResource(R.string.save_title),
        yesText = stringResource(id = R.string.save),
        noText = stringResource(id = R.string.cancel),
        onClickYes = {

        },
        onDismiss = { }
    )
}