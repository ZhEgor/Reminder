package com.zhiroke.reminder.feature_words.presentation.screens.categories

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.zhiroke.reminder.core.presentation.base.BaseViewModel
import com.zhiroke.reminder.feature_words.domain.use_case.category.LoadCategories
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class CategoriesViewModel : BaseViewModel() {

    abstract val state: CategoriesState

    abstract fun loadCategories()
    abstract fun navigateToCreateWord()
}

class CategoriesViewModelImpl(
    private val loadCategories: LoadCategories
) : CategoriesViewModel() {

    override var state by mutableStateOf(CategoriesState())

    init {
        loadCategories()
    }

    override fun loadCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            loadCategories.invoke().collect { categories ->
                state = state.copy(isLoading = false, categories = categories)
            }
        }
    }

    override fun navigateToCreateWord() {

    }
}
