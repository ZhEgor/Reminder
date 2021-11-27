package com.example.reminder.data.repository

import com.example.reminder.data.entity.Word

interface WordEditor {

    fun addWord(word: Word)

}