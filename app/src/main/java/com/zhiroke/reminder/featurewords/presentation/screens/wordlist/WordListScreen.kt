package com.zhiroke.reminder.featurewords.presentation.screens.wordlist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import com.zhiroke.reminder.core.presentation.components.animatedShimmerBrush
import com.zhiroke.reminder.featurewords.presentation.screens.wordlist.components.ShimmeringWordListItem
import com.zhiroke.reminder.featurewords.presentation.screens.wordlist.components.WordListItem
import com.zhiroke.reminder.featurewords.presentation.screens.wordlist.components.WordListItemExpanded

@Composable
fun WordListScreen(
    navController: NavController,
    viewModel: WordListViewModel
) {
    val state = viewModel.state

    if (state.words.value.isNotEmpty()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            itemsIndexed(items = state.words.value) { position, word ->
                if (position >= state.words.value.size - 5 && !state.endReached.value && !state.isPageLoading.value) {
                    viewModel.loadWordsNextWords()
                }
                if (state.focusedWord.value?.id != word.id) {
                    WordListItem(
                        modifier = Modifier
                            .padding(horizontal = 8.dp, vertical = 6.dp)
                            .fillMaxSize()
                            .clip(RoundedCornerShape(4.dp))
                            .background(MaterialTheme.colors.primary)
                            .clickable {
                                state.focusedWord.value = word
                            },
                        position = position + 1,
                        word = word
                    )
                } else {
                    WordListItemExpanded(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(horizontal = 8.dp, vertical = 6.dp)
                            .fillMaxSize()
                            .clip(RoundedCornerShape(4.dp))
                            .background(MaterialTheme.colors.primary)
                            .clickable {
                                state.focusedWord.value = null
                            },
                        word = word,
                        position = position + 1,
                        onSave = {

                        }, onDelete = {

                        }
                    )
                }
            }
            item {
                if (state.isPageLoading.value) {
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
    } else if (state.isLoading.value) {
        val brush = animatedShimmerBrush(
            shimmerColors = listOf(
                MaterialTheme.colors.primary.copy(alpha = 0.6f),
                MaterialTheme.colors.primary.copy(alpha = 0.2f),
                MaterialTheme.colors.primary.copy(alpha = 0.6f),
            )
        )
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(30) {
                ShimmeringWordListItem(
                    modifier = Modifier
                        .height(42.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 6.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(brush)
                )
            }
        }
    } else {
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
    }
}