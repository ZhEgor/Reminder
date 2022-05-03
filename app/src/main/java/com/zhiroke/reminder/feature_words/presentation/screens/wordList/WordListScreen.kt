package com.zhiroke.reminder.feature_words.presentation.screens.wordList

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.zhiroke.reminder.feature_words.presentation.screens.wordList.components.WordListItem

@Composable
fun WordListScreen(
    navController: NavController,
    viewModel: WordListViewModel
) {
    val state = viewModel.state

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(state.words.size) { position ->
            if (position >= state.words.size - 5 && !state.endReached && !state.isLoading) {
                viewModel.loadWordsNextWords()
            }
            WordListItem(
                position = position + 1,
                word = state.words[position],
                modifier = Modifier
                    .padding(4.dp) // margin
                    .padding(4.dp) // padding
                    .fillMaxSize()
                    .clip(RoundedCornerShape(4.dp))
                    .background(MaterialTheme.colors.primary)
                    .clickable {

                    }
            )
        }
        item {
            if (state.isLoading) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }

//    Scaffold(
//        floatingActionButton = {
//            FloatingActionButton(onClick = {
//
//            })
//        }
//    )
}