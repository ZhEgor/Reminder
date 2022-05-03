package com.zhiroke.reminder.feature_words.presentation.screens.wordList

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.zhiroke.reminder.core.presentation.base.BaseViewModel
import com.zhiroke.reminder.core.presentation.util.DefaultPaginatorImpl
import com.zhiroke.reminder.feature_words.domain.model.Category
import com.zhiroke.reminder.feature_words.domain.model.Word
import com.zhiroke.reminder.feature_words.domain.use_case.word.WordUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class WordListViewModel : BaseViewModel() {

    abstract val state: WordListState

    abstract fun loadWordsNextWords()
    abstract fun updateWord(word: Word)
    abstract fun deleteWord(word: Word)
}

class WordListViewModelImpl(
    private val category: Category,
    private val wordUseCase: WordUseCase
) : WordListViewModel() {

    override var state by mutableStateOf(WordListState())
    private val paginator = DefaultPaginatorImpl(
        initialKey = START_POSITION,
        onLoadUpdated = {
            withContext(Dispatchers.Main) {
                state = state.copy(
                    isLoading = it
                )
            }
        }, onRequest = { nextPosition ->
            wordUseCase.loadWordsFromPosition(category.id, nextPosition, PAGINATION_LIMIT)
        }, getNextKey = { words ->
            words.size
        }, onError = {
            state = state.copy(error = it?.localizedMessage)
        }, onSuccess = { words, nextKey ->
            state = state.copy(
                words = state.words + words,
                position = nextKey,
                endReached = words.size < PAGINATION_LIMIT
            )
        }
    )

    init {
        Log.d("tag", "wordlist vm${category.name}")
        loadWordsNextWords()
    }

    override fun loadWordsNextWords() {
        viewModelScope.launch(Dispatchers.IO) {
            paginator.loadNextItems()
        }
    }

    override fun updateWord(word: Word) {
        viewModelScope.launch(Dispatchers.IO) {
            state = state.copy(isLoading = true)
            val hasWordUpdated = wordUseCase.updateWord(word)
            state = if (hasWordUpdated) {
                val words = state.words.map {
                    if (it.id == word.id) word
                    else it
                }
                state.copy(
                    isLoading = false,
                    words = words
                )
            } else {
                state.copy(isLoading = false)
            }
        }
    }

    override fun deleteWord(word: Word) {
        viewModelScope.launch(Dispatchers.IO) {
            state = state.copy(isLoading = true)
            val hasWordDeleted = wordUseCase.deleteWords(listOf(word))
            state = if (hasWordDeleted) {
                val words = state.words.filterNot { it.id == word.id }
                state.copy(
                    isLoading = false,
                    words = words
                )
            } else {
                state.copy(isLoading = false)
            }
        }
    }

    companion object {

        private const val PAGINATION_LIMIT = 30
        private const val START_POSITION = 0
    }
}
