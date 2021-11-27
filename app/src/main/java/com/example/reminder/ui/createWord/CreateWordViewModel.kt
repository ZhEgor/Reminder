package com.example.reminder.ui.createWord

import com.example.reminder.base.BaseViewModel
import com.example.reminder.data.entity.Word
import com.example.reminder.events.CreateWordEvent
import com.example.reminder.states.CreateWordState
import com.example.reminder.useCase.AddNewWordUseCase
import java.util.*

abstract class CreateWordViewModel(addNewWordUseCase: AddNewWordUseCase) :
    BaseViewModel<CreateWordState, CreateWordEvent>(
        useCases = setOf(addNewWordUseCase),
        reducer = CreateWordReducer()
    ) {

    abstract fun addNewWord(
        spelling: String,
        translation: String,
        pronunciation: String,
    )

}

class CreateWordViewModelImpl(addNewWordUseCase: AddNewWordUseCase) :
    CreateWordViewModel(addNewWordUseCase) {

    override fun addNewWord(
        spelling: String,
        translation: String,
        pronunciation: String,
    ) {
        event(
            CreateWordEvent.AddNewWord(
                Word(
                    id = UUID.randomUUID().toString(),
                    spelling = spelling,
                    translation = translation,
                    pronunciation = pronunciation,
                    creationDate = System.currentTimeMillis().toString(),
                    lastShowDate = "",
                    complexity = 1,
                    repetitionsShowed = 0
                )
            )
        )

    }

}