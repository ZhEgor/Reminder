package com.zhiroke.reminder.data.repository.room

import com.zhiroke.reminder.data.entity.Category
import com.zhiroke.reminder.data.repository.CategoryEditor
import com.zhiroke.reminder.data.repository.CategoryFetcher
import com.zhiroke.reminder.data.repository.room.dao.CategoryDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class CategoryRoomDataSource(
    private val categoryDao: CategoryDao
) : CategoryEditor, CategoryFetcher {

    override suspend fun addCategory(category: Category) {
        categoryDao.addCategory(category)
    }

    override suspend fun updateCategory(category: Category) {
        categoryDao.updateCategory(category)
    }

    override suspend fun fetchCategories() = categoryDao.readAllCategories()
}
