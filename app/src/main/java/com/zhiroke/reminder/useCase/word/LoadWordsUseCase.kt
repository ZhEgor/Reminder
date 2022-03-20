package com.zhiroke.reminder.useCase.word

import com.zhiroke.reminder.data.entity.Word
import com.zhiroke.reminder.data.repository.WordFetcher
import kotlinx.coroutines.flow.Flow

class LoadWordsUseCase(private val wordFetcher: WordFetcher) {

    suspend fun invoke(): Flow<List<Word>> {
        return wordFetcher.fetchWords()
    }
}
