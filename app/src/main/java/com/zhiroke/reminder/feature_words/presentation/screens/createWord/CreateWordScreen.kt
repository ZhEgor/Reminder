package com.zhiroke.reminder.feature_words.presentation.screens.createWord

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.zhiroke.reminder.feature_words.presentation.screens.createWord.components.CreateWordField
import com.zhiroke.reminder.feature_words.presentation.screens.createWord.components.Dash
import org.koin.androidx.compose.getViewModel
import timber.log.Timber

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CreateWordScreen(
    navController: NavController?,
    viewModel: CreateWordViewModel = getViewModel()
) {
    Timber.d("CreateWordScreen")
    val scrollState = rememberScrollState()
    val uiState = viewModel.uiState
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
        Text(text = "Word creator", style = MaterialTheme.typography.h5)
        CreateWordField(
            title = "Spelling",
            value = uiState.spellingState,
            onValueChange = { uiState.spellingState = it }
        )
        CreateWordField(
            title = "Translation",
            value = uiState.translationState,

            onValueChange = { uiState.translationState = it }
        )
        CreateWordField(
            title = "Pronunciation",
            value = uiState.pronunciationState,
            onValueChange = { uiState.pronunciationState = it }
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 32.dp),
            onClick = { viewModel.onEvent(CreateWordEvent.AddWord) }
        ) {
            Text(text = "Add", style = MaterialTheme.typography.h6)
        }
    }
}

@Preview
@Composable
fun PreviewCreateWordScreen() {
    CreateWordScreen(null)
}