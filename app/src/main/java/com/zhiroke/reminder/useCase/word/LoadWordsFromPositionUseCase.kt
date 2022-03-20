package com.zhiroke.reminder.useCase.word

import com.zhiroke.reminder.data.entity.Word
import com.zhiroke.reminder.data.repository.WordFetcher

class LoadWordsFromPositionUseCase(private val wordFetcher: WordFetcher) {

    suspend fun invoke(categoryId: String, position: Int, limit: Int): List<Word>  {
        return wordFetcher.fetchWordsFromPosition(categoryId, position, limit)
    }
}