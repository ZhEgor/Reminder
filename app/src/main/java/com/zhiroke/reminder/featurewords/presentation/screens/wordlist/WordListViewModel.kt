package com.zhiroke.reminder.featurewords.presentation.screens.wordlist

import androidx.lifecycle.viewModelScope
import com.zhiroke.reminder.core.presentation.base.BaseViewModel
import com.zhiroke.reminder.core.presentation.util.DefaultPaginatorImpl
import com.zhiroke.reminder.featurewords.domain.model.Category
import com.zhiroke.reminder.featurewords.domain.model.Word
import com.zhiroke.reminder.featurewords.domain.usecase.ValidationTextFieldUseCase
import com.zhiroke.reminder.featurewords.domain.usecase.word.WordUseCase
import com.zhiroke.reminder.featurewords.domain.util.ext.cleanup
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
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

    private val paginator = DefaultPaginatorImpl(
        initialKey = START_POSITION,
        onLoadUpdated = {
            withContext(Dispatchers.Main) {
                state.isPageLoading.value = it
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
            state.isLoading.value = false
        }
    )

    init {
        loadWordsNextWords()
        observerLastAddedWord()
    }

    private fun observerLastAddedWord() {
        viewModelScope.launch(Dispatchers.IO) {
            wordUseCase.lastAddedWordFlow().collectLatest { word ->
                state.words.value = listOf(word) + state.words.value
            }
        }
    }

    override fun loadWordsNextWords() {
        viewModelScope.launch(Dispatchers.IO) {
            paginator.loadNextItems()
        }
    }

    override fun updateWord(word: Word) {
        val cleanWord = word.cleanup()
        if (!validateWord(cleanWord)) return
        viewModelScope.launch(Dispatchers.IO) {
            state.isPageLoading.value = true
            val hasWordUpdated = wordUseCase.updateWord(cleanWord)
            if (hasWordUpdated) {
                val words = state.words.value.map {
                    if (it.id == cleanWord.id) cleanWord
                    else it
                }
                state.words.value = words
            }
            state.isPageLoading.value = false
        }
    }

    private fun validateWord(word: Word): Boolean {
        var isValid = true
        var hasSpellingError = false
        var hasTranslationError = false

        if (!ValidationTextFieldUseCase.isValidSpelling(word.spelling)) {
            isValid = false
            hasSpellingError = true
        }
        if (!ValidationTextFieldUseCase.isValidTranslation(word.translation)) {
            isValid = false
            hasTranslationError = true
        }
        if (!isValid) {
            state.wordListItemExpandedState.value = state.wordListItemExpandedState.value.copy(
                hasSpellingError = hasSpellingError,
                hasTranslationError = hasTranslationError
            )
        }
        return isValid
    }

    override fun deleteWord(word: Word) {
        viewModelScope.launch(Dispatchers.IO) {
            state.isPageLoading.value = true
            val hasWordDeleted = wordUseCase.deleteWords(listOf(word))
            if (hasWordDeleted) {
                val words = state.words.value.filterNot { it.id == word.id }
                state.words.value = words
            }
            state.isPageLoading.value = false
        }
    }

    companion object {

        private const val PAGINATION_LIMIT = 30
        private const val START_POSITION = 0
    }
}
