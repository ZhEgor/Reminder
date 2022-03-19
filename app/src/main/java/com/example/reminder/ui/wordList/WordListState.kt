package com.example.reminder.ui.wordList

import com.example.reminder.data.entity.Word

sealed class WordListState {
    class IsLoading(val state: Boolean) : WordListState()
    class ReceivedWords(val words: List<Word>) : WordListState()
}
