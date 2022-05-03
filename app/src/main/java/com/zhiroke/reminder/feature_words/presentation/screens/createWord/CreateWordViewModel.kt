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
    abstract val categories: List<Category>
    abstract val uiState: CreateWordUIState
    abstract fun onEvent(event: CreateWordEvent)
}

class CreateWordViewModelImpl(
    private val wordUseCase: WordUseCase,
    private val categoryUseCase: CategoryUseCase
) : CreateWordViewModel() {

    override var state by mutableStateOf(CreateWordState())
    override val uiState = CreateWordUIState()
    override val categories: List<Category>
        get() = _categories
    private var _categories: List<Category> = emptyList()
    private var getCategoriesJob: Job? = null

    override fun onEvent(event: CreateWordEvent) {
        when (event) {
            is CreateWordEvent.AddWord -> {
                addNewWord(
                    spelling = uiState.spellingState,
                    translation = uiState.translationState,
                    pronunciation = uiState.pronunciationState,
                    categoryId = "e53b2e67-0028-4dbc-b10b-71f3d51ad5dd"
                )
                // 1	e53b2e67-0028-4dbc-b10b-71f3d51ad5dd	A Song Of Ice and Fire	English	1648458870663
                // 2	e02435bf-4452-44d4-9931-3eff4c149d73	Harry Potter	English	1648456864582
            }
        }
    }

    private fun addNewWord(
        spelling: String,
        translation: String,
        pronunciation: String,
        categoryId: String
    ) {
        if (!validateFieldsSpelling(spelling)) return
        if (!validateFieldsTranslation(translation)) return
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

    fun loadCategories() {
        getCategoriesJob?.cancel()
        getCategoriesJob = categoryUseCase.loadCategories().onEach { categories ->
            state = state.copy(isLoading = false, categories = categories)
            _categories = categories
        }.launchIn(viewModelScope)
    }

    private fun validateFieldsSpelling(spelling: String): Boolean {
        return ValidationEditTextUseCase.validateSpelling(spelling)
    }

    private fun validateFieldsTranslation(translation: String): Boolean {
        return ValidationEditTextUseCase.validateTranslation(translation)
    }
}
