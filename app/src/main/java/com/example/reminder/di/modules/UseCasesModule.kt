package com.example.reminder.di.modules

import com.example.reminder.useCase.AddNewWordUseCase
import com.example.reminder.useCase.LoadWordsUseCase
import org.koin.dsl.module

val useCasesModule = module {

    factory { AddNewWordUseCase(get()) }
    factory { LoadWordsUseCase(get()) }

}