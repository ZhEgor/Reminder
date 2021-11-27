package com.example.reminder.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reminder.util.delegate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class BaseViewModel<State, Event>(
    private val useCases: Set<UseCase<State, Event>>,
    private val reducer: Reducer<State, Event>
) : ViewModel() {

    private val mutableState = MutableLiveData(reducer.initialState)
    private var stateValue by mutableState.delegate()
    val state: LiveData<State> = mutableState

    protected fun event(event: Event) {
        stateValue = reducer.reduce(stateValue, event)
        useCases.filter { it.canHandle(event) }.forEach { useCase ->
            viewModelScope.launch(Dispatchers.IO) {
                useCase.invoke(stateValue, event).collect {
                    withContext(Dispatchers.Main) {
                        event(event)
                    }
                }
            }
//            disposables.add(
//                useCase.invoke(stateValue, event)
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(this::event, Throwable::printStackTrace)
//            )
        }
    }

}