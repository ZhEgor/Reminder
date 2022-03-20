package com.zhiroke.reminder.data.repository

import com.zhiroke.reminder.data.entity.Word

interface WordEditor {

    suspend fun addWord(word: Word)

}