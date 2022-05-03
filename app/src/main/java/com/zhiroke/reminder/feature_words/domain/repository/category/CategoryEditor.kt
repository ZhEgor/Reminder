package com.zhiroke.reminder.feature_words.domain.repository.category

import com.zhiroke.reminder.feature_words.domain.model.Category

interface CategoryEditor {

    suspend fun addCategory(category: Category)

    suspend fun updateCategory(category: Category)

}