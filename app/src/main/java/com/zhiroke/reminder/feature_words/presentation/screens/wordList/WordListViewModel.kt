package com.zhiroke.reminder.feature_words.presentation.screens.wordList

import android.util.Log
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

    val state = WordListState()

    abstract fun loadWordsNextWords()
    abstract fun updateWord(word: Word)
    abstract fun deleteWord(word: Word)
}

class WordListViewModelImpl(
    private val category: Category,
    private val wordUseCase: WordUseCase
) : WordListViewModel() {

    private val id: Int = (0..99999).random()

    private val paginator = DefaultPaginatorImpl(
        initialKey = START_POSITION,
        onLoadUpdated = {
            withContext(Dispatchers.Main) {
                state.isLoading.value = it
            }
        }, onRequest = { nextPosition ->
            wordUseCase.loadWordsFromPosition(category.id, nextPosition, PAGINATION_LIMIT)
        }, getNextKey = { words ->
            words.size
        }, onError = {
            state.error.value = it?.localizedMessage
        }, onSuccess = { words, nextKey ->
            state.words.value = state.words.value + words
            state.position.value = nextKey
            state.endReached.value = words.size < PAGINATION_LIMIT
        }
    )

    init {
        Log.d("tag", "idViewModel: $id")
        loadWordsNextWords()
    }

    override fun loadWordsNextWords() {
        viewModelScope.launch(Dispatchers.IO) {
            paginator.loadNextItems()
        }
    }

    override fun updateWord(word: Word) {
        viewModelScope.launch(Dispatchers.IO) {
            state.isLoading.value = true
            val hasWordUpdated = wordUseCase.updateWord(word)
            if (hasWordUpdated) {
                val words = state.words.value.map {
                    if (it.id == word.id) word
                    else it
                }
                state.words.value = words
            }
            state.isLoading.value = false
        }
    }

    override fun deleteWord(word: Word) {
        viewModelScope.launch(Dispatchers.IO) {
            state.isLoading.value = true
            val hasWordDeleted = wordUseCase.deleteWords(listOf(word))
            if (hasWordDeleted) {
                val words = state.words.value.filterNot { it.id == word.id }
                state.words.value = words
            }
            state.isLoading.value = false
        }
    }

    companion object {

        private const val PAGINATION_LIMIT = 30
        private const val START_POSITION = 0
    }
}
