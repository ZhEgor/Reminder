package com.zhiroke.reminder.ui.wordList

import androidx.lifecycle.viewModelScope
import com.zhiroke.reminder.base.BaseViewModel
import com.zhiroke.reminder.data.entity.Word
import com.zhiroke.reminder.useCase.word.LoadWordsFromPositionUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class WordListViewModel : BaseViewModel() {

    abstract val state: StateFlow<WordListState>

    abstract fun loadWordsFromPosition(position: Int, limit: Int)
    abstract fun navigateToWordDetailsScreen(word: Word)
}

class WordListViewModelImpl(
    private val categoryId: String,
    private val loadWordsFromPositionUseCase: LoadWordsFromPositionUseCase
) : WordListViewModel() {

    override val state = MutableStateFlow(WordListState())
    private var words: List<Word> = emptyList()

    init {
        loadWordsFromPosition(START_POSITION, START_LIMIT)
    }

    override fun loadWordsFromPosition(position: Int, limit: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            words = words + loadWordsFromPositionUseCase.invoke(categoryId, position, limit)
            state.value = state.value.copy(isLoading = false, words = words)
        }
    }

    override fun navigateToWordDetailsScreen(word: Word) {

    }

    companion object {

        private const val START_LIMIT = 30
        private const val START_POSITION = 0
    }
}
