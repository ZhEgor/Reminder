package com.zhiroke.reminder.feature_words.presentation.screens.wordList

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.zhiroke.reminder.R
import com.zhiroke.reminder.feature_words.presentation.screens.wordList.components.WordListItem

@Composable
fun WordListScreen(
    navController: NavController,
    viewModel: WordListViewModel
) {
    val state = viewModel.state

    if (state.words.isEmpty()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier.size(width = 200.dp, height = 160.dp),
                painter = painterResource(id = R.drawable.ic_empty),
                contentDescription = "empty state",
                tint = Color.Unspecified
            )
            Text(
                modifier = Modifier.width(250.dp),
                text = stringResource(R.string.no_words_state),
                textAlign = TextAlign.Center,
            )
        }
    } else {
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
    }
}