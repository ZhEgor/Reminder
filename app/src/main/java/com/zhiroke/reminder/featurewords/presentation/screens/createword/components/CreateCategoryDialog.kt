package com.zhiroke.reminder.featurewords.presentation.screens.createword.components

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.zhiroke.reminder.R
import com.zhiroke.reminder.featurewords.presentation.screens.createword.CreateWordEvent
import com.zhiroke.reminder.featurewords.presentation.screens.createword.CreateWordViewModel

@Composable
fun CreateCategoryDialog(viewModel: CreateWordViewModel) {
    val uiState = viewModel.uiState

    if (uiState.isCreateCategoryDialogActive) {
        Dialog(onDismissRequest = { uiState.isCreateCategoryDialogActive = false }) {
            Surface(
                modifier = Modifier
                    .width(300.dp)
                    .wrapContentHeight()
                    .padding(8.dp),
                shape = RoundedCornerShape(8.dp),
                color = MaterialTheme.colors.surface
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(R.string.create_category),
                        style = MaterialTheme.typography.h5
                    )
                    OutlinedTextField(
                        value = uiState.categoryNameState.text,
                        onValueChange = {
                            uiState.categoryNameState =
                                uiState.categoryNameState.copy(text = it, hasError = false)
                        },
                        label = {
                            Text(text = stringResource(id = R.string.category_name))
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedLabelColor = MaterialTheme.colors.primaryVariant,
                            focusedBorderColor = MaterialTheme.colors.primaryVariant,
                            cursorColor = MaterialTheme.colors.primaryVariant
                        ),
                        singleLine = true,
                        isError = uiState.categoryNameState.hasError
                    )
                    OutlinedTextField(
                        value = uiState.categoryLanguageState.text,
                        onValueChange = {
                            uiState.categoryLanguageState =
                                uiState.categoryLanguageState.copy(text = it, hasError = false)
                        },
                        label = {
                            Text(text = stringResource(id = R.string.language))
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedLabelColor = MaterialTheme.colors.primaryVariant,
                            focusedBorderColor = MaterialTheme.colors.primaryVariant,
                            cursorColor = MaterialTheme.colors.primaryVariant
                        ),
                        singleLine = true,
                        isError = uiState.categoryLanguageState.hasError
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
                            onClick = { uiState.isCreateCategoryDialogActive = false },
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
                                    contentDescription = "cancel category creation",
                                    tint = MaterialTheme.colors.onPrimary
                                )
                                Text(
                                    text = stringResource(id = R.string.cancel),
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
                                viewModel.onEvent(
                                    CreateWordEvent.AddCategory(
                                        categoryName = uiState.categoryNameState.text,
                                        language = uiState.categoryLanguageState.text
                                    )
                                )
                            },
                            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primaryVariant)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    modifier = Modifier.padding(end = 4.dp),
                                    imageVector = Icons.Default.Done,
                                    contentDescription = "create new category"
                                )
                                Text(text = stringResource(id = R.string.create))
                            }
                        }
                    }
                }
            }
        }
    }
}