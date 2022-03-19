package com.example.reminder.useCase.category

import com.example.reminder.data.entity.Category
import com.example.reminder.data.repository.CategoryFetcher
import kotlinx.coroutines.flow.Flow

class LoadCategoriesUseCase(private val categoryFetcher: CategoryFetcher) {

    suspend fun invoke(): Flow<List<Category>> {
        return categoryFetcher.fetchCategories()
    }

}
