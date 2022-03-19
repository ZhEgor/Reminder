package com.example.reminder.ui.wordListHolder

import androidx.lifecycle.viewModelScope
import com.example.reminder.base.BaseViewModel
import com.example.reminder.useCase.category.LoadCategoriesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

abstract class WordListHolderViewModel : BaseViewModel() {

    abstract val state: SharedFlow<WordListHolderState>

    abstract fun loadCategories()
}

class WordListHolderViewModelImpl(
    private val loadCategoriesUseCase: LoadCategoriesUseCase
) : WordListHolderViewModel() {

    override val state = MutableSharedFlow<WordListHolderState>()

    init {
        loadCategories()
    }

    override fun loadCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            state.emit(WordListHolderState.IsLoading(true))
            loadCategoriesUseCase.invoke().collect { categories ->
                state.emit(WordListHolderState.ReceivedCategories(categories))
                state.emit(WordListHolderState.IsLoading(false))
            }
        }
    }
}
