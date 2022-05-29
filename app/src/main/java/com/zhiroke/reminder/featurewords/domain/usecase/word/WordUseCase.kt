package com.zhiroke.reminder.featurewords.domain.usecase.word

data class WordUseCase(
    val addWord: AddWord,
    val deleteWords: DeleteWords,
    val updateWord: UpdateWord,
    val loadWordsFromPosition: LoadWordsFromPosition,
    val lastAddedWordFlow: LastAddedWordFlow
)
