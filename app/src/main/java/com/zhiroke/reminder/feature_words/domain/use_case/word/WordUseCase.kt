package com.zhiroke.reminder.feature_words.domain.use_case.word

data class WordUseCase(
    val addWord: AddWord,
    val deleteWords: DeleteWords,
    val updateWord: UpdateWord,
    val loadWordsFromPosition: LoadWordsFromPosition
)
