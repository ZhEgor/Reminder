package com.zhiroke.reminder.di.modules

import com.zhiroke.reminder.feature_words.domain.use_case.category.AddCategory
import com.zhiroke.reminder.feature_words.domain.use_case.category.CategoryUseCase
import com.zhiroke.reminder.feature_words.domain.use_case.category.LoadCategories
import com.zhiroke.reminder.feature_words.domain.use_case.category.UpdateCategory
import com.zhiroke.reminder.feature_words.domain.use_case.word.*
import org.koin.dsl.module

val useCasesModule = module {

    /* Word */
    factory { WordUseCase(get(), get(), get(), get()) }
    factory { AddWord(get()) }
    factory { UpdateWord(get()) }
    factory { DeleteWords(get()) }
    factory { LoadWordsFromPosition(get()) }

    /* Category */
    factory { CategoryUseCase(get(), get(), get()) }
    factory { AddCategory(get()) }
    factory { LoadCategories(get()) }
    factory { UpdateCategory(get()) }

}