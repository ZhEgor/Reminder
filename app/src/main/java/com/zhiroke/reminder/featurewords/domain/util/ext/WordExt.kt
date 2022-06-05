package com.zhiroke.reminder.featurewords.domain.util.ext

import com.zhiroke.reminder.featurewords.domain.model.Word

fun Word.cleanup() = this.copy(
    spelling = spelling.formatField(),
    translation = translation.formatField(),
    pronunciation = pronunciation.formatField()
)