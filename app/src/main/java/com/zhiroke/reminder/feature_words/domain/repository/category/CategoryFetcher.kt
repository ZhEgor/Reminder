package com.zhiroke.reminder.feature_words.domain.repository.category

import com.zhiroke.reminder.feature_words.domain.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoryFetcher {

    fun fetchCategories(): Flow<List<Category>>

}