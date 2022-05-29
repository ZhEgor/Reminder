package com.zhiroke.reminder.featurewords.presentation.screens.createword

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.zhiroke.reminder.core.presentation.base.BaseViewModel
import com.zhiroke.reminder.featurewords.domain.model.Category
import com.zhiroke.reminder.featurewords.domain.model.Word
import com.zhiroke.reminder.featurewords.domain.usecase.ValidationEditTextUseCase
import com.zhiroke.reminder.featurewords.domain.usecase.category.CategoryUseCase
import com.zhiroke.reminder.featurewords.domain.usecase.word.WordUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.*

abstract class CreateWordViewModel : BaseViewModel() {

    abstract val state: CreateWordState
    abstract val uiState: CreateWordUIState
    abstract fun onEvent(event: CreateWordEvent)
}

class CreateWordViewModelImpl(
    private val wordUseCase: WordUseCase,
    private val categoryUseCase: CategoryUseCase
) : CreateWordViewModel() {

    override var state by mutableStateOf(CreateWordState())
    override val uiState = CreateWordUIState()
    private var categories: List<Category> = emptyList()
    private var getCategoriesJob: Job? = null

    init {
        loadCategories()
    }

    override fun onEvent(event: CreateWordEvent) {
        when (event) {
            is CreateWordEvent.AddWord -> {
                addNewWord(
                    spelling = event.spelling,
                    translation = event.translation,
                    pronunciation = event.pronunciation,
                    categoryId = event.category?.id
                )
            }
            is CreateWordEvent.AddCategory -> {
                addNewCategory(
                    name = event.categoryName,
                    language = event.language
                )
            }
            is CreateWordEvent.FindCategories -> {
                findCategories(event.categoryName)
            }
        }
    }

    private fun findCategories(categoryName: String) {
        uiState.resultCategoriesState = if (categoryName.isBlank()) {
            categories
        } else {
            categories.filter { category ->
                category.name.contains(categoryName, ignoreCase = true)
            }
        }
    }

    private fun addNewWord(
        spelling: String,
        translation: String,
        pronunciation: String,
        categoryId: String?
    ) {
        var isFieldsValid = true
        if (!ValidationEditTextUseCase.isValidSpelling(spelling)) {
            uiState.spellingState = uiState.spellingState.copy(hasError = true)
            isFieldsValid = false
        }
        if (!ValidationEditTextUseCase.isValidTranslation(translation)) {
            uiState.translationState = uiState.translationState.copy(hasError = true)
            isFieldsValid = false
        }
        if (categoryId == null) {
            uiState.categoryFieldState = uiState.categoryFieldState.copy(hasError = true)
            isFieldsValid = false
        }
        if (!isFieldsValid) return
        viewModelScope.launch(Dispatchers.IO) {
            wordUseCase.addWord(
                Word(
                    id = UUID.randomUUID().toString(),
                    spelling = spelling,
                    translation = translation,
                    pronunciation = pronunciation,
                    categoryId = categoryId!!,
                    creationDate = System.currentTimeMillis().toString(),
                    lastShowDate = "",
                    complexity = 1,
                    repetitionsShowed = 0
                )
            )
            categories.find { category -> category.id == categoryId }?.let { category ->
                updateCategory(category)
            }
            uiState.spellingState = uiState.spellingState.copy(text = "")
            uiState.translationState = uiState.translationState.copy(text = "")
            uiState.pronunciationState = uiState.pronunciationState.copy(text = "")
            state = state.copy(isLoading = false)
        }
    }

    private fun addNewCategory(name: String, language: String) {
        var isFieldsValid = true
        if (!ValidationEditTextUseCase.isValidCategoryName(name)) {
            uiState.categoryNameState = uiState.categoryNameState.copy(hasError = true)
            isFieldsValid = false
        }
        if (!ValidationEditTextUseCase.isValidLanguage(language)) {
            uiState.categoryLanguageState = uiState.categoryLanguageState.copy(hasError = true)
            isFieldsValid = false
        }
        if (!isFieldsValid) return
        viewModelScope.launch(Dispatchers.IO) {
            state = state.copy(isLoading = true)
            val newCategory = Category(
                id = UUID.randomUUID().toString(),
                name = name,
                language = language,
                lastInteractedTime = System.currentTimeMillis().toString(),
            )
            categoryUseCase.addCategory(newCategory)
            uiState.isCreateCategoryDialogActive = false
            uiState.categoryNameState = uiState.categoryNameState.copy(text = "")
            uiState.categoryLanguageState = uiState.categoryLanguageState.copy(text = "")
            state = state.copy(isLoading = false)
        }
    }

    private fun updateCategory(category: Category) {
        viewModelScope.launch(Dispatchers.IO) {
            categoryUseCase.updateCategory(
                category.copy(
                    lastInteractedTime = System.currentTimeMillis().toString()
                )
            )
        }
    }

    private fun loadCategories() {
        getCategoriesJob?.cancel()
        getCategoriesJob = categoryUseCase.loadCategories().onEach { categories ->
            this.categories = categories
            uiState.categoryFieldState = uiState.categoryFieldState.copy(category = categories.firstOrNull())
            findCategories(uiState.categorySearchFieldState.text)
            state = state.copy(isLoading = false)
        }.launchIn(viewModelScope)
    }
}
