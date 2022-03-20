package com.zhiroke.reminder.useCase.category

import com.zhiroke.reminder.data.entity.Category
import com.zhiroke.reminder.data.repository.CategoryEditor

class AddNewCategoryUseCase(private val categoryEditor: CategoryEditor) {

    suspend fun invoke(category: Category) {
        categoryEditor.addCategory(category)
    }

}