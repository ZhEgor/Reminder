package com.example.reminder.useCase

import com.example.reminder.base.UseCase
import com.example.reminder.data.repository.WordFetcher
import com.example.reminder.events.WordListEvent
import com.example.reminder.states.WordListState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LoadWordsUseCase(private val wordFetcher: WordFetcher) :
    UseCase<WordListState, WordListEvent> {

    override fun invoke(state: WordListState, event: WordListEvent): Flow<WordListEvent> {
        return if (event is WordListEvent.StartLoading) {
//            wordFetcher.fetchWords().collect {
//                withContext()
//            }
            flow {
                emit(WordListEvent.StartLoading)
            }
        } else {
            flow {
                emit(WordListEvent.Error(IllegalAccessException("Wrong event: $event")))
            }
        }
    }

    override fun canHandle(event: WordListEvent) = false
}
