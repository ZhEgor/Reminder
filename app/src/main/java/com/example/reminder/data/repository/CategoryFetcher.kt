package com.example.reminder.data.repository

import com.example.reminder.data.entity.Category
import kotlinx.coroutines.flow.Flow

interface CategoryFetcher {

    suspend fun fetchCategories(): Flow<List<Category>>

}