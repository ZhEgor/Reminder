package com.zhiroke.reminder.featurewords.domain.repository.category

import com.zhiroke.reminder.featurewords.domain.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoryFetcher {

    fun fetchCategories(): Flow<List<Category>>

}