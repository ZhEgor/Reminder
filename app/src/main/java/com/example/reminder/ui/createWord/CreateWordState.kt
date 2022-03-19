package com.example.reminder.ui.createWord

import com.example.reminder.data.entity.Category

sealed class CreateWordState {
    class IsLoading(val state: Boolean) : CreateWordState()
    class ReceivedCategories(val categories: List<Category>) : CreateWordState()
}
