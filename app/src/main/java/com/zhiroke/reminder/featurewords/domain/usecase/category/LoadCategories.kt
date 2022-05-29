package com.zhiroke.reminder.featurewords.domain.usecase.category

import com.zhiroke.reminder.featurewords.domain.model.Category
import com.zhiroke.reminder.featurewords.domain.repository.category.CategoryFetcher
import kotlinx.coroutines.flow.Flow

class LoadCategories(private val categoryFetcher: CategoryFetcher) {

    operator fun invoke(): Flow<List<Category>> {
        return categoryFetcher.fetchCategories()
    }
}
