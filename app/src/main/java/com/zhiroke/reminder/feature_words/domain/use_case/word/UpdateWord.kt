package com.zhiroke.reminder.feature_words.domain.use_case.word

import com.zhiroke.reminder.feature_words.domain.model.Word
import com.zhiroke.reminder.feature_words.domain.repository.word.WordEditor

class UpdateWord(private val wordEditor: WordEditor) {

    suspend operator fun invoke(word: Word): Boolean {
        return wordEditor.updateWord(word)
    }
}
