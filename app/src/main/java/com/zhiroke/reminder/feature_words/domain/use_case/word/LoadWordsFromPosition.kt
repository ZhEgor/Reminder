package com.zhiroke.reminder.feature_words.domain.use_case.word

import com.zhiroke.reminder.feature_words.domain.model.Word
import com.zhiroke.reminder.feature_words.domain.repository.word.WordFetcher

class LoadWordsFromPosition(private val wordFetcher: WordFetcher) {

    suspend operator fun invoke(categoryId: String, position: Int, limit: Int): Result<List<Word>>  {
        return wordFetcher.fetchWordsFromPosition(categoryId, position, limit)
    }
}