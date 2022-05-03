package com.zhiroke.reminder.core.presentation.util

interface Paginator<Key, Item> {
    suspend fun loadNextItems()
    fun reset()
}
