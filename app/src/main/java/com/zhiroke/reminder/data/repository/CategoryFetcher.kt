package com.zhiroke.reminder.data.repository

import com.zhiroke.reminder.data.entity.Category
import kotlinx.coroutines.flow.Flow

interface CategoryFetcher {

    suspend fun fetchCategories(): Flow<List<Category>>

}