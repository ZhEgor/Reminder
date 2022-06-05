package com.zhiroke.reminder.featurewords.presentation.screens.categories

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.zhiroke.reminder.core.presentation.base.BaseViewModel
import com.zhiroke.reminder.featurewords.domain.usecase.category.LoadCategories
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class CategoriesViewModel : BaseViewModel() {

    abstract val state: CategoriesState
}

class CategoriesViewModelImpl(
    private val loadCategories: LoadCategories
) : CategoriesViewModel() {

    override var state by mutableStateOf(CategoriesState())

    init {
        loadCategories()
    }

    private fun loadCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            loadCategories.invoke().collect { categories ->
                withContext(Dispatchers.Main) {
                    state = state.copy(isLoading = false, categories = categories)
                }
            }
        }
    }
}
