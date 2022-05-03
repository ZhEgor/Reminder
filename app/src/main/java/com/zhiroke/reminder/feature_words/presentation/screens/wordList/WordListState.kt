package com.zhiroke.reminder.feature_words.presentation.screens.wordList

import com.zhiroke.reminder.feature_words.domain.model.Word

data class WordListState(
    val isLoading: Boolean = true,
    val words: List<Word> = emptyList(),
    val error: String? = null,
    val endReached: Boolean = false,
    val position: Int = 0
) {
    fun onEvent(event: WordListEvent) {
        when(event) {
        }
    }
}
