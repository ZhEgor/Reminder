package com.zhiroke.reminder.feature_words.data.repository.room

import com.zhiroke.reminder.feature_words.domain.model.Category
import com.zhiroke.reminder.feature_words.domain.repository.category.CategoryEditor
import com.zhiroke.reminder.feature_words.domain.repository.category.CategoryFetcher
import com.zhiroke.reminder.feature_words.data.data_source.dao.CategoryDao

class CategoryRoomRepository(
    private val categoryDao: CategoryDao
) : CategoryEditor, CategoryFetcher {

    override suspend fun addCategory(category: Category) {
        categoryDao.addCategory(category)
    }

    override suspend fun updateCategory(category: Category) {
        categoryDao.updateCategory(category)
    }

    override fun fetchCategories() = categoryDao.readAllCategories()
}
