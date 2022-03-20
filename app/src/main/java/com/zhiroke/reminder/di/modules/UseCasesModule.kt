package com.zhiroke.reminder.di.modules

import com.zhiroke.reminder.useCase.*
import com.zhiroke.reminder.useCase.category.AddNewCategoryUseCase
import com.zhiroke.reminder.useCase.category.LoadCategoriesUseCase
import com.zhiroke.reminder.useCase.category.UpdateCategoryUseCase
import com.zhiroke.reminder.useCase.word.AddNewWordUseCase
import com.zhiroke.reminder.useCase.word.LoadWordsFromPositionUseCase
import com.zhiroke.reminder.useCase.word.LoadWordsUseCase
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