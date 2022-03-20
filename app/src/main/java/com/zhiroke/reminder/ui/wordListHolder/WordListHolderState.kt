package com.zhiroke.reminder.ui.wordListHolder

import com.zhiroke.reminder.data.entity.Category

data class WordListHolderState(
    val isLoading: Boolean = true,
    val categories: List<Category> = emptyList()
)
