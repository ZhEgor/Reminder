package com.zhiroke.reminder.featurewords.domain.usecase.word

import com.zhiroke.reminder.featurewords.domain.model.Word
import com.zhiroke.reminder.featurewords.domain.repository.word.WordFetcher

class LoadWordsFromPosition(private val wordFetcher: WordFetcher) {

    suspend operator fun invoke(categoryId: String, position: Int, limit: Int): Result<List<Word>>  {
        return wordFetcher.fetchWordsFromPosition(categoryId, position, limit)
    }
}