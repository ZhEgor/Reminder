package com.zhiroke.reminder.ui.wordListHolder

import androidx.lifecycle.viewModelScope
import com.zhiroke.reminder.base.BaseViewModel
import com.zhiroke.reminder.navigation.Command
import com.zhiroke.reminder.navigation.Router
import com.zhiroke.reminder.ui.createWord.CreateWordScreen
import com.zhiroke.reminder.useCase.category.LoadCategoriesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

abstract class WordListHolderViewModel : BaseViewModel() {

    abstract val state: StateFlow<WordListHolderState>

    abstract fun loadCategories()
    abstract fun navigateToCreateWord()
}

class WordListHolderViewModelImpl(
    private val loadCategoriesUseCase: LoadCategoriesUseCase,
    private val router: Router
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

    override fun navigateToCreateWord() {
        router.execute(
            Command.Navigate(CreateWordScreen(CreateWordScreen.From.WordListHolder))
        )
    }
}
