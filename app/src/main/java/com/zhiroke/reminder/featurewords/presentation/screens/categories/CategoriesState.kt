package com.zhiroke.reminder.featurewords.presentation.screens.categories

import com.zhiroke.reminder.featurewords.domain.model.Category

data class CategoriesState(
    val isLoading: Boolean = true,
    val categories: List<Category> = emptyList()
)
