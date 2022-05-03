package com.zhiroke.reminder.feature_words.domain.use_case.word

import com.zhiroke.reminder.feature_words.domain.model.Word
import com.zhiroke.reminder.feature_words.domain.repository.word.WordEditor

class DeleteWords(private val wordEditor: WordEditor) {

    suspend operator fun invoke(words: List<Word>): Boolean {
        return wordEditor.deleteWords(words) != 0
    }
}
