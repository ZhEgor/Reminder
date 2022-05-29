package com.zhiroke.reminder.featurewords.domain.repository.category

import com.zhiroke.reminder.featurewords.domain.model.Category

interface CategoryEditor {

    suspend fun addCategory(category: Category)

    suspend fun updateCategory(category: Category)

}