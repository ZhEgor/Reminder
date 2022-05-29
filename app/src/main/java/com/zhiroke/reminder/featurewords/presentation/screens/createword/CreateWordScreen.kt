package com.zhiroke.reminder.featurewords.presentation.screens.createword

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.zhiroke.reminder.R
import com.zhiroke.reminder.featurewords.presentation.screens.createword.components.*
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CreateWordScreen(
    navController: NavController?,
    viewModel: CreateWordViewModel = getViewModel()
) {
    val uiState = viewModel.uiState
    val scrollState = rememberScrollState()
    val isSelectCategoryDialogActive = remember { mutableStateOf(false) }

    SelectCategoryDialog(isDialogActive = isSelectCategoryDialogActive, viewModel = viewModel)
    CreateCategoryDialog(viewModel = viewModel)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Dash(
            modifier = Modifier
                .width(80.dp)
                .padding(16.dp)
        )
        Text(text = stringResource(R.string.word_creator), style = MaterialTheme.typography.h5)
        CreateWordField(
            title = stringResource(R.string.spelling),
            value = uiState.spellingState.text,
            hasError = uiState.spellingState.hasError
        ) {
            uiState.spellingState = uiState.spellingState.copy(text = it, hasError = false)
        }
        CreateWordField(
            title = stringResource(R.string.translation),
            value = uiState.translationState.text,
            hasError = uiState.translationState.hasError
        ) {
            uiState.translationState = uiState.translationState.copy(text = it, hasError = false)
        }
        CreateWordField(
            title = stringResource(R.string.pronunciation),
            value = uiState.pronunciationState.text,
            hasError = uiState.pronunciationState.hasError
        ) {
            uiState.pronunciationState = uiState.pronunciationState.copy(text = it, hasError = false)
        }
        SelectCategoryField(text = uiState.categoryFieldState.category?.name) {
            isSelectCategoryDialogActive.value = true
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 32.dp),
            onClick = {
                viewModel.onEvent(
                    CreateWordEvent.AddWord(
                        spelling = uiState.spellingState.text,
                        translation = uiState.translationState.text,
                        pronunciation = uiState.pronunciationState.text,
                        category = uiState.categoryFieldState.category
                    )
                )
            }
        ) {
            Text(text = stringResource(R.string.add), style = MaterialTheme.typography.h6)
        }
    }
}

@Preview
@Composable
fun PreviewCreateWordScreen() {
    CreateWordScreen(null)
}