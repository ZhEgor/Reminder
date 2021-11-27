package com.example.reminder.base

interface Reducer<State, Event> {

    val initialState: State

    fun reduce(state: State, event: Event): State

}