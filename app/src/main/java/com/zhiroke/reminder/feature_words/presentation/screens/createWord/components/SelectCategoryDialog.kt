package com.zhiroke.reminder.feature_words.presentation.screens.createWord.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.zhiroke.reminder.R
import com.zhiroke.reminder.feature_words.presentation.screens.createWord.CreateWordEvent
import com.zhiroke.reminder.feature_words.presentation.screens.createWord.CreateWordViewModel

@Composable
fun SelectCategoryDialog(
    isDialogActive: MutableState<Boolean>,
    viewModel: CreateWordViewModel
) {
    val uiState = viewModel.uiState
    val isCreateCategoryDialogActive = remember { mutableStateOf(false) }

    CreateCategoryDialog(isDialogActive = isCreateCategoryDialogActive, viewModel = viewModel)
    if (isDialogActive.value) {
        Dialog(onDismissRequest = { isDialogActive.value = false }) {
            Surface(
                modifier = Modifier
                    .width(300.dp)
                    .height(450.dp)
                    .padding(4.dp),
                shape = RoundedCornerShape(8.dp),
                color = MaterialTheme.colors.surface
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                ) {
                    OutlinedTextField(
                        value = uiState.categorySearchFieldState,
                        onValueChange = {
                            viewModel.onEvent(CreateWordEvent.FindCategories(it))
                            uiState.categorySearchFieldState = it
                        },
                        trailingIcon = {
                            if (uiState.categorySearchFieldState.isNotEmpty()) {
                                IconButton(onClick = { uiState.categorySearchFieldState = "" }) {
                                    Icon(
                                        imageVector = Icons.Filled.Cancel,
                                        contentDescription = "remove text"
                                    )
                                }
                            }
                        },
                        leadingIcon = {
                            IconButton(onClick = {
                                viewModel.onEvent(CreateWordEvent.FindCategories(uiState.categorySearchFieldState))
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = "Search by text"
                                )
                            }
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedLabelColor = MaterialTheme.colors.primaryVariant,
                            focusedBorderColor = MaterialTheme.colors.primaryVariant,
                            cursorColor = MaterialTheme.colors.primaryVariant
                        )
                    )
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(vertical = 8.dp)
                    ) {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 4.dp, vertical = 2.dp)
                                    .clip(RoundedCornerShape(4.dp))
                                    .clickable {
                                        isCreateCategoryDialogActive.value = true
                                        isDialogActive.value = false
                                    }
                                    .border(
                                        width = 1.dp,
                                        color = MaterialTheme.colors.primaryVariant,
                                        shape = RoundedCornerShape(4.dp)
                                    )
                            ) {
                                Text(
                                    modifier = Modifier.padding(4.dp),
                                    text = stringResource(R.string.create_new),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                        items(uiState.resultCategoriesState.size) { position ->
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 4.dp, vertical = 2.dp)
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(MaterialTheme.colors.primaryVariant)
                                    .clickable {
                                        uiState.selectedCategory =
                                            uiState.resultCategoriesState[position]
                                        isDialogActive.value = false
                                    }
                            ) {
                                Text(
                                    modifier = Modifier.padding(4.dp),
                                    text = uiState.resultCategoriesState[position].name,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}