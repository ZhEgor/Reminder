package com.example.reminder.ui.wordList

import com.example.reminder.base.BaseViewModel
import com.example.reminder.events.WordListEvent
import com.example.reminder.states.WordListState
import com.example.reminder.useCase.LoadWordsUseCase

abstract class WordListViewModel(loadWordsUseCase: LoadWordsUseCase) :
    BaseViewModel<WordListState, WordListEvent>(
        useCases = setOf(loadWordsUseCase),
        reducer = WordListReducer()
    ) {
    // TODO: Implement the ViewModel
}

class WordListViewModelImpl(loadWordsUseCase: LoadWordsUseCase) :
    WordListViewModel(loadWordsUseCase) {
    // TODO: Implement the ViewModel
}