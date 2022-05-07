package com.zhiroke.reminder.feature_words.presentation.screens.createWord

import com.zhiroke.reminder.feature_words.domain.model.Category

sealed class CreateWordEvent {

    data class FindCategories(val categoryName: String) : CreateWordEvent()
    data class AddWord(
        val spelling: String,
        val translation: String,
        val pronunciation: String,
        val category: Category?
    ) : CreateWordEvent()
    data class AddCategory(val categoryName: String, val language: String) : CreateWordEvent()
}
