package com.example.reminder.events

sealed class WordListEvent {

    object None : WordListEvent()
    object StartLoading : WordListEvent()
    object StopLoading : WordListEvent()
    data class Error(val error: Exception) : WordListEvent()

}