package com.example.reminder.useCase.word

import com.example.reminder.data.entity.Word
import com.example.reminder.data.repository.WordFetcher

class LoadWordsFromPositionUseCase(private val wordFetcher: WordFetcher) {

    suspend fun invoke(categoryId: String, position: Int, limit: Int): List<Word>  {
        return wordFetcher.fetchWordsFromPosition(categoryId, position, limit)
    }
}