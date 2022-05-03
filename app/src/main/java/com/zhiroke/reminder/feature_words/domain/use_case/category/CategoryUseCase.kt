package com.zhiroke.reminder.feature_words.domain.use_case.category

data class CategoryUseCase(
    val addCategory: AddCategory,
    val loadCategories: LoadCategories,
    val updateCategory: UpdateCategory
)
