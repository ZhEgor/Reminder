package com.example.reminder.ui.wordList

import com.example.reminder.base.Reducer
import com.example.reminder.events.WordListEvent
import com.example.reminder.states.WordListState

class WordListReducer : Reducer<WordListState, WordListEvent> {

    override val initialState = WordListState(
        isLoading = false,
        words = listOf()
    )

    override fun reduce(state: WordListState, event: WordListEvent): WordListState {
        return when(event) {
            WordListEvent.None -> {
                state
            }
            WordListEvent.StartLoading -> {
                state.copy(isLoading = true)
            }
            WordListEvent.StopLoading -> {
                state.copy(isLoading = false)
            }
            is WordListEvent.Error -> {
                state
            }
        }
    }

}
