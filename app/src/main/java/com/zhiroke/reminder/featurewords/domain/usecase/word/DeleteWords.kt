package com.zhiroke.reminder.featurewords.domain.usecase.word

import com.zhiroke.reminder.featurewords.domain.model.Word
import com.zhiroke.reminder.featurewords.domain.repository.word.WordEditor

class DeleteWords(private val wordEditor: WordEditor) {

    suspend operator fun invoke(words: List<Word>): Boolean {
        return wordEditor.deleteWords(words) != 0
    }
}
