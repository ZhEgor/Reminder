package com.zhiroke.reminder.ui.screens.createWord

import com.zhiroke.reminder.data.entity.Category

data class CreateWordState(
    val isLoading: Boolean = true,
    val categories: List<Category> = emptyList()
)
