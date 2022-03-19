package com.example.reminder.ui.createWord

import androidx.lifecycle.viewModelScope
import com.example.reminder.base.BaseViewModel
import com.example.reminder.data.entity.Category
import com.example.reminder.data.entity.Word
import com.example.reminder.useCase.*
import com.example.reminder.useCase.category.AddNewCategoryUseCase
import com.example.reminder.useCase.category.LoadCategoriesUseCase
import com.example.reminder.useCase.category.UpdateCategoryUseCase
import com.example.reminder.useCase.word.AddNewWordUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*

abstract class CreateWordViewModel : BaseViewModel() {

    abstract val state: SharedFlow<CreateWordState>
    abstract val categories: List<Category>

    abstract fun addNewWord(
        spelling: String,
        translation: String,
        pronunciation: String,
        categoryId: String
    )

    abstract fun addNewCategory(name: String, language: String)
    abstract fun loadCategories()
    abstract fun validateFieldsSpelling(spelling: String): Boolean
    abstract fun validateFieldsTranslation(translation: String): Boolean
}

class CreateWordViewModelImpl(
    private val addNewWordUseCase: AddNewWordUseCase,
    private val addNewCategoryUseCase: AddNewCategoryUseCase,
    private val updateCategoryUseCase: UpdateCategoryUseCase,
    private val loadCategoriesUseCase: LoadCategoriesUseCase,
    private val validationEditTextUseCase: ValidationEditTextUseCase
) : CreateWordViewModel() {

    override val state = MutableSharedFlow<CreateWordState>()
    override val categories: List<Category>
        get() = _categories
    private var _categories: List<Category> = emptyList()

    override fun addNewWord(
        spelling: String,
        translation: String,
        pronunciation: String,
        categoryId: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            state.emit(CreateWordState.IsLoading(true))
            addNewWordUseCase.invoke(
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
            state.emit(CreateWordState.IsLoading(false))
        }
    }

    override fun addNewCategory(name: String, language: String) {
        viewModelScope.launch(Dispatchers.IO) {
            state.emit(CreateWordState.IsLoading(true))
            val newCategory = Category(
                id = UUID.randomUUID().toString(),
                name = name,
                language = language,
                lastViewedTime = System.currentTimeMillis().toString(),
            )
            addNewCategoryUseCase.invoke(newCategory)
            state.emit(CreateWordState.IsLoading(false))
        }
    }

    private fun updateCategory(category: Category) {
        viewModelScope.launch(Dispatchers.IO) {
            updateCategoryUseCase.invoke(
                category.copy(
                    lastViewedTime = System.currentTimeMillis().toString()
                )
            )
        }
    }

    override fun loadCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            state.emit(CreateWordState.IsLoading(true))
            loadCategoriesUseCase.invoke().collect { categories ->
                state.emit(CreateWordState.ReceivedCategories(categories))
                state.emit(CreateWordState.IsLoading(false))
                _categories = categories
            }
        }
    }

    override fun validateFieldsSpelling(spelling: String): Boolean {
        return validationEditTextUseCase.validateSpelling(spelling)
    }

    override fun validateFieldsTranslation(translation: String): Boolean {
        return validationEditTextUseCase.validateTranslation(translation)
    }
}
