package com.zhiroke.reminder.featurewords.presentation.screens.wordlist

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.zhiroke.reminder.featurewords.domain.model.Word
import com.zhiroke.reminder.featurewords.presentation.screens.wordlist.components.WordListItemExpandedState

data class WordListState(
    var isLoading: MutableState<Boolean> = mutableStateOf(true),
    var words: MutableState<List<Word>> = mutableStateOf(emptyList()),
    var error: MutableState<String?> = mutableStateOf(null),
    var isPageLoading: MutableState<Boolean> = mutableStateOf(false),
    var endReached: MutableState<Boolean> = mutableStateOf(false),
    var position: MutableState<Int> = mutableStateOf(0),
    var focusedWord: MutableState<Word?> = mutableStateOf(null),
    var wordListItemExpandedState: MutableState<WordListItemExpandedState> = mutableStateOf(
        WordListItemExpandedState()
    ),
    var deleteWord: Word? = null,
    var saveWord: Word? = null
)