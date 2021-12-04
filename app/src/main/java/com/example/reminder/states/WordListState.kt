package com.example.reminder.states

import com.example.reminder.data.entity.Word

data class WordListState(
    val isLoading: Boolean = true,
    val words: List<Word> = emptyList()
)