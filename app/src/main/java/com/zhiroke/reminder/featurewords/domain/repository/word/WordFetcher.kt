package com.zhiroke.reminder.featurewords.domain.repository.word

import com.zhiroke.reminder.featurewords.domain.model.Word

interface WordFetcher {

    suspend fun fetchWordsFromPosition(categoryId: String, position: Int, limit: Int): Result<List<Word>>
}
