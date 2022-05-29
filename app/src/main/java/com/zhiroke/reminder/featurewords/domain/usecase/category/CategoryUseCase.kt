package com.zhiroke.reminder.featurewords.domain.usecase.category

data class CategoryUseCase(
    val addCategory: AddCategory,
    val loadCategories: LoadCategories,
    val updateCategory: UpdateCategory
)
