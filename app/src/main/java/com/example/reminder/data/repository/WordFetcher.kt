package com.example.reminder.data.repository

import com.example.reminder.data.entity.Word
import kotlinx.coroutines.flow.Flow

interface WordFetcher {

    fun fetchWords(): Flow<List<Word>>

}