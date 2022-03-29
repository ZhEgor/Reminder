package com.zhiroke.reminder.ui.screens.wordList

import com.zhiroke.reminder.data.entity.Word

data class WordListState(
    val isLoading: Boolean = true,
    val words: List<Word> = emptyList(),
    var hasWordDeleted: Boolean? = null,
    var hasWordUpdated: Boolean? = null
)
