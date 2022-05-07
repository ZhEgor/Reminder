package com.zhiroke.reminder.feature_words.presentation.screens.createWord

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.zhiroke.reminder.core.presentation.states.TextFieldState
import com.zhiroke.reminder.feature_words.domain.model.Category

data class CreateWordState(
    val isLoading: Boolean = true
)

class CreateWordUIState {
    var isCreateCategoryDialogActive: Boolean by mutableStateOf(false)

    var spellingState: TextFieldState by mutableStateOf(TextFieldState())
    var translationState: TextFieldState by mutableStateOf(TextFieldState())
    var pronunciationState: TextFieldState by mutableStateOf(TextFieldState())
    var categoryFieldState: CategoryFieldState by mutableStateOf(CategoryFieldState())
    var categorySearchFieldState: TextFieldState by mutableStateOf(TextFieldState())
    var resultCategoriesState: List<Category> by mutableStateOf(emptyList())
    var categoryNameState: TextFieldState by mutableStateOf(TextFieldState())
    var categoryLanguageState: TextFieldState by mutableStateOf(TextFieldState())
}

data class CategoryFieldState(val category: Category? = null, val hasError: Boolean = false)