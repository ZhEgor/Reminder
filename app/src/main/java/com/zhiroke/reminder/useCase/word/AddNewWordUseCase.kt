package com.zhiroke.reminder.useCase.word

import com.zhiroke.reminder.data.entity.Word
import com.zhiroke.reminder.data.repository.WordEditor

class AddNewWordUseCase(private val wordEditor: WordEditor) {

    suspend fun invoke(word: Word) {
        wordEditor.addWord(word)
    }

}