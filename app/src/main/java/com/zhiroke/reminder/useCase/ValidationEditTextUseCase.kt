package com.zhiroke.reminder.useCase

class ValidationEditTextUseCase {

    fun validateSpelling(spelling: String): Boolean {
        return spelling.isNotBlank()
    }

    fun validateTranslation(translation: String): Boolean {
        return translation.isNotBlank()
    }

}