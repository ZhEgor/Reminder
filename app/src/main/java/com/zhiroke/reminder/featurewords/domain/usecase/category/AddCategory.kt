package com.zhiroke.reminder.featurewords.domain.usecase.category

import com.zhiroke.reminder.featurewords.domain.model.Category
import com.zhiroke.reminder.featurewords.domain.repository.category.CategoryEditor

class AddCategory(private val categoryEditor: CategoryEditor) {

    suspend operator fun invoke(category: Category) {
        categoryEditor.addCategory(category)
    }
}
