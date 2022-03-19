package com.example.reminder.data.repository

import com.example.reminder.data.entity.Category

interface CategoryEditor {

    suspend fun addCategory(category: Category)

    suspend fun updateCategory(category: Category)

}