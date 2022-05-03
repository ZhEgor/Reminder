package com.zhiroke.reminder.feature_words.presentation.screens.categories

import com.zhiroke.reminder.feature_words.domain.model.Category

data class CategoriesState(
    val isLoading: Boolean = true,
    val categories: List<Category> = emptyList()
)
