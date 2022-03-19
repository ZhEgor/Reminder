package com.example.reminder.data.repository.room

import com.example.reminder.data.entity.Category
import com.example.reminder.data.repository.CategoryEditor
import com.example.reminder.data.repository.CategoryFetcher
import com.example.reminder.data.repository.room.dao.CategoryDao
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
