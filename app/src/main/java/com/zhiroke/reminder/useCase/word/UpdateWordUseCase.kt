package com.zhiroke.reminder.useCase.word

import com.zhiroke.reminder.data.entity.Word
import com.zhiroke.reminder.data.repository.WordEditor

class UpdateWordUseCase(private val wordEditor: WordEditor) {

    suspend fun invoke(word: Word): Boolean {
        return wordEditor.updateWord(word)
    }
}
