package com.zhiroke.reminder.data.repository

import com.zhiroke.reminder.data.entity.Category

interface CategoryEditor {

    suspend fun addCategory(category: Category)

    suspend fun updateCategory(category: Category)

}