package com.zhiroke.reminder.useCase.category

import com.zhiroke.reminder.data.entity.Category
import com.zhiroke.reminder.data.repository.CategoryFetcher
import kotlinx.coroutines.flow.Flow

class LoadCategoriesUseCase(private val categoryFetcher: CategoryFetcher) {

    suspend fun invoke(): Flow<List<Category>> {
        return categoryFetcher.fetchCategories()
    }

}
