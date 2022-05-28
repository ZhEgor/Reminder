package com.zhiroke.reminder.feature_words.domain.repository.word

import com.zhiroke.reminder.feature_words.domain.model.Word
import kotlinx.coroutines.flow.Flow

interface WordFetcher {

    suspend fun fetchWordsFromPosition(categoryId: String, position: Int, limit: Int): Result<List<Word>>
}
