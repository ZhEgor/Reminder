package com.zhiroke.reminder.core.presentation.util.paginator

interface Paginator<Key, Item> {
    suspend fun loadNextItems()
    fun reset()
}
