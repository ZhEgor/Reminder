package com.zhiroke.reminder.feature_words.presentation.screens.createWord

sealed class CreateWordEvent {
    object AddWord : CreateWordEvent()
}