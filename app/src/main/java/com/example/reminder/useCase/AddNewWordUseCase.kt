package com.example.reminder.useCase

import com.example.reminder.base.UseCase
import com.example.reminder.data.repository.WordEditor
import com.example.reminder.events.CreateWordEvent
import com.example.reminder.states.CreateWordState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AddNewWordUseCase(private val wordEditor: WordEditor) :
    UseCase<CreateWordState, CreateWordEvent> {

    override fun invoke(state: CreateWordState, event: CreateWordEvent): Flow<CreateWordEvent> {
        return if (event is CreateWordEvent.AddNewWord) {
            wordEditor.addWord(event.word)
            flow {
                emit(CreateWordEvent.AddedNewWord)
            }
        } else {
            flow {
                emit(CreateWordEvent.Error(IllegalAccessException("Wrong event: $event")))
            }
        }
    }

    override fun canHandle(event: CreateWordEvent) = event is CreateWordEvent.AddNewWord

}