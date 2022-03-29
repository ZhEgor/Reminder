package com.zhiroke.reminder.di.modules

import com.zhiroke.reminder.useCase.category.AddNewCategoryUseCase
import com.zhiroke.reminder.useCase.category.LoadCategoriesUseCase
import com.zhiroke.reminder.useCase.category.UpdateCategoryUseCase
import com.zhiroke.reminder.useCase.word.*
import org.koin.dsl.module

val useCasesModule = module {

    /* Word */
    factory { AddNewWordUseCase(get()) }
    factory { UpdateWordUseCase(get()) }
    factory { DeleteWordsUseCase(get()) }
    factory { LoadWordsUseCase(get()) }
    factory { LoadWordsFromPositionUseCase(get()) }

    /* Category */
    factory { AddNewCategoryUseCase(get()) }
    factory { LoadCategoriesUseCase(get()) }
    factory { UpdateCategoryUseCase(get()) }

}