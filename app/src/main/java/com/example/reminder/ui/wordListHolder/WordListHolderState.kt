package com.example.reminder.ui.wordListHolder

import com.example.reminder.data.entity.Category

sealed class WordListHolderState {
    class IsLoading(val state: Boolean) : WordListHolderState()
    class ReceivedCategories(val categories: List<Category>) : WordListHolderState()
}
