package com.example.reminder.data.repository

import com.example.reminder.data.entity.Word

interface WordEditor {

    suspend fun addWord(word: Word)

}