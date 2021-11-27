package com.example.reminder.events

import com.example.reminder.data.entity.Word

sealed class CreateWordEvent {

    object None : CreateWordEvent()
    object StartLoading : CreateWordEvent()
    object StopLoading : CreateWordEvent()
    object AddedNewWord : CreateWordEvent()
    data class AddNewWord(val word: Word) : CreateWordEvent()
    data class Error(val error: Exception) : CreateWordEvent()

}