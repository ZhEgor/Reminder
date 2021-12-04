package com.example.reminder.useCase

import com.example.reminder.data.entity.Word
import com.example.reminder.data.repository.WordFetcher
import kotlinx.coroutines.flow.Flow

class LoadWordsUseCase(private val wordFetcher: WordFetcher) {

    fun invoke(): Flow<List<Word>> {
        return wordFetcher.fetchWords()
    }

}
