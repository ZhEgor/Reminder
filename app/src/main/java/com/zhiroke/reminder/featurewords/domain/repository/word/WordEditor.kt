package com.zhiroke.reminder.featurewords.domain.repository.word

import com.zhiroke.reminder.featurewords.domain.model.Word
import kotlinx.coroutines.flow.SharedFlow

interface WordEditor {

    val lastAddedWordFlow: SharedFlow<Word>

    suspend fun addWord(word: Word)
    suspend fun updateWord(word: Word): Boolean
    suspend fun deleteWords(words: List<Word>): Int
}