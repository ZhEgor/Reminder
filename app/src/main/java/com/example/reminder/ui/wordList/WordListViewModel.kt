package com.example.reminder.ui.wordList

import androidx.lifecycle.viewModelScope
import com.example.reminder.base.BaseViewModel
import com.example.reminder.data.entity.Word
import com.example.reminder.states.WordListState
import com.example.reminder.useCase.LoadWordsUseCase
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

abstract class WordListViewModel : BaseViewModel() {

    abstract val state: StateFlow<WordListState>

    abstract fun loadWords()

    abstract fun navigateToWordDetailsScreen (word: Word)

}

class WordListViewModelImpl(private val loadWordsUseCase: LoadWordsUseCase) : WordListViewModel() {

    override val state = MutableStateFlow(WordListState())

    init {
        loadWords()
    }

    override fun loadWords() {
        viewModelScope.launch {
            loadWordsUseCase.invoke().collect { words ->
                state.value = state.value.copy(
                    isLoading = false,
                    words = words
                )
                cancel()
            }
        }
    }

    override fun navigateToWordDetailsScreen(word: Word) {

    }

}