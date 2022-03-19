package com.example.reminder.data.repository

import com.example.reminder.data.entity.Word
import kotlinx.coroutines.flow.Flow

interface WordFetcher {

    suspend fun fetchWords(): Flow<List<Word>>
    suspend fun fetchWordsFromPosition(categoryId: String, position: Int, limit: Int): List<Word>
}
