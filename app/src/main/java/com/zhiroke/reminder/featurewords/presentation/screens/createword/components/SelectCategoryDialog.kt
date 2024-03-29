package com.zhiroke.reminder.featurewords.presentation.screens.createword.components

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.zhiroke.reminder.R
import com.zhiroke.reminder.featurewords.presentation.screens.createword.CreateWordEvent
import com.zhiroke.reminder.featurewords.presentation.screens.createword.CreateWordViewModel

@Composable
fun SelectCategoryDialog(
    isDialogActive: MutableState<Boolean>,
    viewModel: CreateWordViewModel
) {
    val uiState = viewModel.uiState

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
                        value = uiState.categorySearchFieldState.text,
                        onValueChange = {
                            viewModel.onEvent(CreateWordEvent.FindCategories(it))
                            uiState.categorySearchFieldState =
                                uiState.categorySearchFieldState.copy(text = it)
                        },
                        trailingIcon = {
                            if (uiState.categorySearchFieldState.text.isNotEmpty()) {
                                IconButton(onClick = {
                                    uiState.categorySearchFieldState =
                                        uiState.categorySearchFieldState.copy(text = "")
                                    viewModel.onEvent(
                                        CreateWordEvent.FindCategories(uiState.categorySearchFieldState.text)
                                    )
                                }) {
                                    Icon(
                                        imageVector = Icons.Filled.Cancel,
                                        contentDescription = "remove text"
                                    )
                                }
                            }
                        },
                        leadingIcon = {
                            IconButton(
                                onClick = {
                                    viewModel.onEvent(
                                        CreateWordEvent.FindCategories(uiState.categorySearchFieldState.text)
                                    )
                                }
                            ) {
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
                        ),
                        singleLine = true
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
                                        uiState.isCreateCategoryDialogActive = true
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
                        if (uiState.resultCategoriesState.isNotEmpty()) {
                            items(uiState.resultCategoriesState.size) { position ->
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 4.dp, vertical = 2.dp)
                                        .clip(RoundedCornerShape(4.dp))
                                        .background(MaterialTheme.colors.primaryVariant)
                                        .clickable {
                                            uiState.categoryFieldState =
                                                uiState.categoryFieldState.copy(
                                                    category = uiState.resultCategoriesState[position],
                                                    hasError = false
                                                )
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
                        } else {
                            item {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(vertical = 88.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Icon(
                                        modifier = Modifier.size(
                                            width = 150.dp,
                                            height = 100.dp
                                        ),
                                        painter = painterResource(id = R.drawable.ic_no_search_result),
                                        contentDescription = "no search result",
                                        tint = Color.Unspecified
                                    )
                                    Text(
                                        modifier = Modifier.width(200.dp),
                                        text = stringResource(R.string.no_search_result_state),
                                        textAlign = TextAlign.Center,
                                    )
                                }

                            }
                        }
                    }
                }

            }
        }
    }
}