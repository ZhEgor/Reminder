package com.example.reminder.ui.wordList

import androidx.lifecycle.viewModelScope
import com.example.reminder.base.BaseViewModel
import com.example.reminder.data.entity.Word
import com.example.reminder.useCase.word.LoadWordsFromPositionUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

abstract class WordListViewModel : BaseViewModel() {
    abstract val state: SharedFlow<WordListState>

    abstract fun loadWordsFromPosition(categoryId: String, position: Int, limit: Int)
    abstract fun navigateToWordDetailsScreen(word: Word)
}

class WordListViewModelImpl(private val loadWordsFromPositionUseCase: LoadWordsFromPositionUseCase) :
    WordListViewModel() {

    override val state = MutableSharedFlow<WordListState>()
    private var words: List<Word> = emptyList()

    override fun loadWordsFromPosition(categoryId: String, position: Int, limit: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            state.emit(WordListState.IsLoading(true))
            words = words + loadWordsFromPositionUseCase.invoke(categoryId, position, limit)
            state.emit(WordListState.ReceivedWords(words))
            state.emit(WordListState.IsLoading(false))
        }
    }

    override fun navigateToWordDetailsScreen(word: Word) {

    }
}
