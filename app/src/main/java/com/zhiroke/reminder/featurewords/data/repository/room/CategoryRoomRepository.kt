package com.zhiroke.reminder.featurewords.data.repository.room

import com.zhiroke.reminder.featurewords.domain.model.Category
import com.zhiroke.reminder.featurewords.domain.repository.category.CategoryEditor
import com.zhiroke.reminder.featurewords.domain.repository.category.CategoryFetcher
import com.zhiroke.reminder.featurewords.data.datasource.dao.CategoryDao

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
