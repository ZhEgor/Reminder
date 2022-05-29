package com.zhiroke.reminder.featurewords.domain.usecase.word

import com.zhiroke.reminder.featurewords.domain.model.Word
import com.zhiroke.reminder.featurewords.domain.repository.word.WordEditor

class UpdateWord(private val wordEditor: WordEditor) {

    suspend operator fun invoke(word: Word): Boolean {
        return wordEditor.updateWord(word)
    }
}
