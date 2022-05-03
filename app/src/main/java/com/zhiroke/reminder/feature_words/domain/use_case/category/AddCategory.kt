package com.zhiroke.reminder.feature_words.domain.use_case.category

import com.zhiroke.reminder.feature_words.domain.model.Category
import com.zhiroke.reminder.feature_words.domain.repository.category.CategoryEditor

class AddCategory(private val categoryEditor: CategoryEditor) {

    suspend operator fun invoke(category: Category) {
        categoryEditor.addCategory(category)
    }
}
