package com.example.reminder.ui.createWord

import com.example.reminder.base.Reducer
import com.example.reminder.events.CreateWordEvent
import com.example.reminder.states.CreateWordState

class CreateWordReducer : Reducer<CreateWordState, CreateWordEvent> {

    override val initialState = CreateWordState(
        isLoading = false,
        wordIsAdded = false
    )

    override fun reduce(state: CreateWordState, event: CreateWordEvent): CreateWordState {
        return when (event) {
            CreateWordEvent.None -> {
                state
            }
            CreateWordEvent.StartLoading -> {
                state.copy(isLoading = true)
            }
            CreateWordEvent.StopLoading -> {
                state.copy(isLoading = false)
            }
            CreateWordEvent.AddedNewWord -> {
                state
            }
            is CreateWordEvent.AddNewWord -> {
                state
            }
            is CreateWordEvent.Error -> {
                state
            }
        }
    }

}