package com.zhiroke.reminder.ui.createWord

import androidx.lifecycle.viewModelScope
import com.zhiroke.reminder.base.BaseViewModel
import com.zhiroke.reminder.data.entity.Category
import com.zhiroke.reminder.data.entity.Word
import com.zhiroke.reminder.useCase.*
import com.zhiroke.reminder.useCase.category.AddNewCategoryUseCase
import com.zhiroke.reminder.useCase.category.LoadCategoriesUseCase
import com.zhiroke.reminder.useCase.category.UpdateCategoryUseCase
import com.zhiroke.reminder.useCase.word.AddNewWordUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*

abstract class CreateWordViewModel : BaseViewModel() {

    abstract val state: StateFlow<CreateWordState>
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

    override val state = MutableStateFlow(CreateWordState())
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
            state.value = state.value.copy(isLoading = false)
        }
    }

    override fun addNewCategory(name: String, language: String) {
        viewModelScope.launch(Dispatchers.IO) {
            state.value = state.value.copy(isLoading = true)
            val newCategory = Category(
                id = UUID.randomUUID().toString(),
                name = name,
                language = language,
                lastInteractedTime = System.currentTimeMillis().toString(),
            )
            addNewCategoryUseCase.invoke(newCategory)
            state.value = state.value.copy(isLoading = false)
        }
    }

    private fun updateCategory(category: Category) {
        viewModelScope.launch(Dispatchers.IO) {
            updateCategoryUseCase.invoke(
                category.copy(
                    lastInteractedTime = System.currentTimeMillis().toString()
                )
            )
        }
    }

    override fun loadCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            loadCategoriesUseCase.invoke().collect { categories ->
                state.value = state.value.copy(isLoading = false, categories = categories)
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
