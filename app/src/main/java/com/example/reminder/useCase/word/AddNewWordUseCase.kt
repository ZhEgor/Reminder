package com.example.reminder.useCase.word

import com.example.reminder.data.entity.Word
import com.example.reminder.data.repository.WordEditor

class AddNewWordUseCase(private val wordEditor: WordEditor) {

    suspend fun invoke(word: Word) {
        wordEditor.addWord(word)
    }

}