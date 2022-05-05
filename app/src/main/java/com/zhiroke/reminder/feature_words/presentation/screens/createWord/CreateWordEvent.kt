package com.zhiroke.reminder.feature_words.presentation.screens.createWord

sealed class CreateWordEvent {

    data class FindCategories(val categoryName: String) : CreateWordEvent()
    object AddWord : CreateWordEvent()
}