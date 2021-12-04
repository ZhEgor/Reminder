package com.example.reminder.ui.createWord

import androidx.lifecycle.viewModelScope
import com.example.reminder.base.BaseViewModel
import com.example.reminder.data.entity.Word
import com.example.reminder.states.CreateWordState
import com.example.reminder.useCase.AddNewWordUseCase
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.*

abstract class CreateWordViewModel : BaseViewModel() {

    abstract val state: StateFlow<CreateWordState>

    abstract fun addNewWord(
        spelling: String,
        translation: String,
        pronunciation: String,
    )

}

class CreateWordViewModelImpl(private val addNewWordUseCase: AddNewWordUseCase) :
    CreateWordViewModel() {

    override val state = MutableStateFlow( CreateWordState())

    override fun addNewWord(
        spelling: String,
        translation: String,
        pronunciation: String,
    ) {
        viewModelScope.launch {

            state.value = state.value.copy(isLoading = true)

            addNewWordUseCase.invoke(
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

            state.value = state.value.copy(isLoading = false)
            cancel()
        }

    }

}
