package com.zhiroke.reminder.feature_words.presentation.screens.wordList

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.zhiroke.reminder.feature_words.domain.model.Word

data class WordListState(
    var words: MutableState<List<Word>> = mutableStateOf(emptyList()),
    var error: MutableState<String?> = mutableStateOf(null),
    var isLoading: MutableState<Boolean> = mutableStateOf(true),
    var endReached: MutableState<Boolean> = mutableStateOf(false),
    var position: MutableState<Int> = mutableStateOf(0)
)