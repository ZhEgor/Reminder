package com.zhiroke.reminder.featurewords.presentation.screens.createword

import com.zhiroke.reminder.featurewords.domain.model.Category

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
