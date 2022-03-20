package com.zhiroke.reminder.useCase.category

import com.zhiroke.reminder.data.entity.Category
import com.zhiroke.reminder.data.repository.CategoryEditor

class UpdateCategoryUseCase(private val categoryEditor: CategoryEditor) {

    suspend fun invoke(category: Category) {
        categoryEditor.updateCategory(category)
    }
}
