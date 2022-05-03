package com.zhiroke.reminder.feature_words.presentation.screens.createWord

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.zhiroke.reminder.feature_words.domain.model.Category

data class CreateWordState(
    val isLoading: Boolean = true,
    val categories: List<Category> = emptyList()
)

class CreateWordUIState {
    var spellingState: String by mutableStateOf("")
    var translationState: String by mutableStateOf("")
    var pronunciationState: String by mutableStateOf("")
}
