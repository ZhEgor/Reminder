package com.zhiroke.reminder.featurewords.domain.usecase.word

import com.zhiroke.reminder.featurewords.domain.model.Word
import com.zhiroke.reminder.featurewords.domain.repository.word.WordEditor

class LastAddedWordFlow(private val wordEditor: WordEditor) {

    operator fun invoke() = wordEditor.lastAddedWordFlow
}
