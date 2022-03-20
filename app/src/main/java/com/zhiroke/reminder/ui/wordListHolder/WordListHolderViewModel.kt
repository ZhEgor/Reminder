package com.zhiroke.reminder.ui.wordListHolder

import androidx.lifecycle.viewModelScope
import com.zhiroke.reminder.base.BaseViewModel
import com.zhiroke.reminder.useCase.category.LoadCategoriesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

abstract class WordListHolderViewModel : BaseViewModel() {

    abstract val state: StateFlow<WordListHolderState>

    abstract fun loadCategories()
}

class WordListHolderViewModelImpl(
    private val loadCategoriesUseCase: LoadCategoriesUseCase
) : WordListHolderViewModel() {

    override val state = MutableStateFlow(WordListHolderState())

    init {
        loadCategories()
    }

    override fun loadCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            loadCategoriesUseCase.invoke().collect { categories ->
                state.value = state.value.copy(isLoading = false, categories = categories)
            }
        }
    }
}
