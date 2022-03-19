package com.example.reminder.di.modules

import com.example.reminder.useCase.*
import com.example.reminder.useCase.category.AddNewCategoryUseCase
import com.example.reminder.useCase.category.LoadCategoriesUseCase
import com.example.reminder.useCase.category.UpdateCategoryUseCase
import com.example.reminder.useCase.word.AddNewWordUseCase
import com.example.reminder.useCase.word.LoadWordsFromPositionUseCase
import com.example.reminder.useCase.word.LoadWordsUseCase
import org.koin.dsl.module

val useCasesModule = module {

    /* Word */
    factory { AddNewWordUseCase(get()) }
    factory { LoadWordsUseCase(get()) }
    factory { LoadWordsFromPositionUseCase(get()) }

    /* Category */
    factory { AddNewCategoryUseCase(get()) }
    factory { LoadCategoriesUseCase(get()) }
    factory { UpdateCategoryUseCase(get()) }

    /* Validation */
    factory { ValidationEditTextUseCase() }

}