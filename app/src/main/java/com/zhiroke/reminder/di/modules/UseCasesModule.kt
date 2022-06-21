package com.zhiroke.reminder.di.modules

import com.zhiroke.reminder.featurewords.domain.usecase.category.AddCategory
import com.zhiroke.reminder.featurewords.domain.usecase.category.CategoryUseCase
import com.zhiroke.reminder.featurewords.domain.usecase.category.LoadCategories
import com.zhiroke.reminder.featurewords.domain.usecase.category.UpdateCategory
import com.zhiroke.reminder.featurewords.domain.usecase.word.*
import org.koin.dsl.module

val useCasesModule = module {

    /* Word */
    factory { WordUseCase(get(), get(), get(), get(), get()) }
    factory { AddWord(get()) }
    factory { UpdateWord(get()) }
    factory { DeleteWords(get()) }
    factory { LoadWordsFromPosition(get()) }
    factory { LastAddedWordFlow(get()) }

    /* Category */
    factory { CategoryUseCase(get(), get(), get()) }
    factory { AddCategory(get()) }
    factory { LoadCategories(get()) }
    factory { UpdateCategory(get()) }

}