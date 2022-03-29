package com.zhiroke.reminder.useCase.word

import com.zhiroke.reminder.data.entity.Word
import com.zhiroke.reminder.data.repository.WordEditor

class DeleteWordsUseCase(private val wordEditor: WordEditor) {

    suspend fun invoke(words: List<Word>): Boolean {
        return wordEditor.deleteWords(words) != 0
    }
}
