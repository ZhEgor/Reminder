package com.zhiroke.reminder.feature_words.domain.use_case

object ValidationEditTextUseCase {

    fun isValidSpelling(spelling: String): Boolean {
        return spelling.isNotBlank()
    }

    fun isValidTranslation(translation: String): Boolean {
        return translation.isNotBlank()
    }

    fun isValidCategoryName(categoryName: String): Boolean {
        return categoryName.isNotBlank()
    }

    fun isValidLanguage(language: String): Boolean {
        return language.isNotBlank()
    }
}
