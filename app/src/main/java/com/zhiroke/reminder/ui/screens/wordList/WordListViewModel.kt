package com.zhiroke.reminder.ui.screens.wordList

import androidx.lifecycle.viewModelScope
import com.zhiroke.reminder.base.BaseViewModel
import com.zhiroke.reminder.data.entity.Word
import com.zhiroke.reminder.useCase.word.DeleteWordsUseCase
import com.zhiroke.reminder.useCase.word.LoadWordsFromPositionUseCase
import com.zhiroke.reminder.useCase.word.UpdateWordUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class WordListViewModel : BaseViewModel() {

    abstract val state: StateFlow<WordListState>

    abstract fun loadWordsFromPosition(position: Int, limit: Int)
    abstract fun updateWord(word: Word)
    abstract fun deleteWord(word: Word)
}

class WordListViewModelImpl(
    private val categoryId: String,
    private val loadWordsFromPositionUseCase: LoadWordsFromPositionUseCase,
    private val updateWordUseCase: UpdateWordUseCase,
    private val deleteWordsUseCase: DeleteWordsUseCase
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

    override fun updateWord(word: Word) {
        viewModelScope.launch(Dispatchers.IO) {
            state.value = state.value.copy(isLoading = true)
            val hasWordUpdated = updateWordUseCase.invoke(word)
            if (hasWordUpdated) {
                words = words.map {
                    if (it.id == word.id) word
                    else it
                }
            }
            state.value = state.value.copy(
                isLoading = false,
                words = words,
                hasWordUpdated = hasWordUpdated
            )
            state.value.hasWordUpdated = null
        }
    }

    override fun deleteWord(word: Word) {
        viewModelScope.launch(Dispatchers.IO) {
            state.value = state.value.copy(isLoading = true)
            val hasWordDeleted = deleteWordsUseCase.invoke(listOf(word))
            if (hasWordDeleted) {
                words = words.filterNot { it.id == word.id }
            }
            state.value = state.value.copy(
                isLoading = false,
                words = words,
                hasWordDeleted = hasWordDeleted
            )
            state.value.hasWordDeleted = null
        }
    }

    companion object {

        private const val START_LIMIT = 30
        private const val START_POSITION = 0
    }
}
