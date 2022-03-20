package com.zhiroke.reminder.ui.wordList

import com.zhiroke.reminder.data.entity.Word

data class WordListState(
    val isLoading: Boolean = true,
    val words: List<Word> = emptyList()
)
