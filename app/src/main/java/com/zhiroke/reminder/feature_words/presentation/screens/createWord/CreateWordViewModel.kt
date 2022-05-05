package com.zhiroke.reminder.feature_words.presentation.screens.createWord

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.zhiroke.reminder.core.presentation.base.BaseViewModel
import com.zhiroke.reminder.feature_words.domain.model.Category
import com.zhiroke.reminder.feature_words.domain.model.Word
import com.zhiroke.reminder.feature_words.domain.use_case.ValidationEditTextUseCase
import com.zhiroke.reminder.feature_words.domain.use_case.category.CategoryUseCase
import com.zhiroke.reminder.feature_words.domain.use_case.word.WordUseCase
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
            CreateWordEvent.AddWord -> {
                addNewWord(
                    spelling = uiState.spellingState,
                    translation = uiState.translationState,
                    pronunciation = uiState.pronunciationState,
                    categoryId = uiState.selectedCategory?.id
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
        if (!validateFieldsSpelling(spelling)) return
        if (!validateFieldsTranslation(translation)) return
        if (categoryId == null) return
        viewModelScope.launch(Dispatchers.IO) {
            wordUseCase.addWord(
                Word(
                    id = UUID.randomUUID().toString(),
                    spelling = spelling,
                    translation = translation,
                    pronunciation = pronunciation,
                    categoryId = categoryId,
                    creationDate = System.currentTimeMillis().toString(),
                    lastShowDate = "",
                    complexity = 1,
                    repetitionsShowed = 0
                )
            )
            categories.find { category -> category.id == categoryId }?.let { category ->
                updateCategory(category)
            }
            state = state.copy(isLoading = false)
            uiState.spellingState = ""
            uiState.translationState = ""
            uiState.pronunciationState = ""
        }
    }

    private fun addNewCategory(name: String, language: String) {
        viewModelScope.launch(Dispatchers.IO) {
            state = state.copy(isLoading = true)
            val newCategory = Category(
                id = UUID.randomUUID().toString(),
                name = name,
                language = language,
                lastInteractedTime = System.currentTimeMillis().toString(),
            )
            categoryUseCase.addCategory(newCategory)
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
            uiState.selectedCategory = categories.firstOrNull()
            findCategories(uiState.categorySearchFieldState)
            state = state.copy(isLoading = false)
        }.launchIn(viewModelScope)
    }

    private fun validateFieldsSpelling(spelling: String): Boolean {
        return ValidationEditTextUseCase.validateSpelling(spelling)
    }

    private fun validateFieldsTranslation(translation: String): Boolean {
        return ValidationEditTextUseCase.validateTranslation(translation)
    }
}
