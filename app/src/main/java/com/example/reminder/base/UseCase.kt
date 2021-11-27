package com.example.reminder.base

import kotlinx.coroutines.flow.Flow

interface UseCase<State, Event> {

    fun invoke(state: State, event: Event): Flow<Event>

    fun canHandle(event: Event): Boolean

}

