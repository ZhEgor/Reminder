package com.example.reminder.useCase.category

import com.example.reminder.data.entity.Category
import com.example.reminder.data.repository.CategoryEditor

class UpdateCategoryUseCase(private val categoryEditor: CategoryEditor) {

    suspend fun invoke(category: Category) {
        categoryEditor.updateCategory(category)
    }
}
