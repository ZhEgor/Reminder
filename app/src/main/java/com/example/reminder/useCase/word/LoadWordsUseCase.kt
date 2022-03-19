package com.example.reminder.useCase.word

import com.example.reminder.data.entity.Word
import com.example.reminder.data.repository.WordFetcher
import kotlinx.coroutines.flow.Flow

class LoadWordsUseCase(private val wordFetcher: WordFetcher) {

    suspend fun invoke(): Flow<List<Word>> {
        return wordFetcher.fetchWords()
    }
}
