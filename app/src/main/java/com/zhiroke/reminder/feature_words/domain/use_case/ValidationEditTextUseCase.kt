package com.zhiroke.reminder.feature_words.domain.use_case

object ValidationEditTextUseCase {

    fun validateSpelling(spelling: String): Boolean {
        return spelling.isNotBlank()
    }

    fun validateTranslation(translation: String): Boolean {
        return translation.isNotBlank()
    }
}
