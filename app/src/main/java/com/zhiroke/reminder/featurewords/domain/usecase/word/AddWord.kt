package com.zhiroke.reminder.featurewords.domain.usecase.word

import com.zhiroke.reminder.featurewords.domain.model.Word
import com.zhiroke.reminder.featurewords.domain.repository.word.WordEditor

class AddWord(private val wordEditor: WordEditor) {

    suspend operator fun invoke(word: Word) {
        wordEditor.addWord(word)
    }
}
