package com.zhiroke.reminder.feature_words.domain.use_case.category

import com.zhiroke.reminder.feature_words.domain.model.Category
import com.zhiroke.reminder.feature_words.domain.repository.category.CategoryFetcher
import kotlinx.coroutines.flow.Flow

class LoadCategories(private val categoryFetcher: CategoryFetcher) {

    operator fun invoke(): Flow<List<Category>> {
        return categoryFetcher.fetchCategories()
    }
}
