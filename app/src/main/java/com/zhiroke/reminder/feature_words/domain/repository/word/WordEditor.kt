package com.zhiroke.reminder.feature_words.domain.repository.word

import com.zhiroke.reminder.feature_words.domain.model.Word

interface WordEditor {

    suspend fun addWord(word: Word)
    suspend fun updateWord(word: Word): Boolean
    suspend fun deleteWords(words: List<Word>): Int
}