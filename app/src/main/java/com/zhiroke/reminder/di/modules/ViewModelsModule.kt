package com.zhiroke.reminder.di.modules

import com.zhiroke.reminder.feature_words.presentation.screens.categories.CategoriesViewModel
import com.zhiroke.reminder.feature_words.presentation.screens.categories.CategoriesViewModelImpl
import com.zhiroke.reminder.feature_words.presentation.screens.createWord.CreateWordViewModel
import com.zhiroke.reminder.feature_words.presentation.screens.createWord.CreateWordViewModelImpl
import com.zhiroke.reminder.feature_words.presentation.screens.wordList.WordListViewModel
import com.zhiroke.reminder.feature_words.presentation.screens.wordList.WordListViewModelImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {

    viewModel<CreateWordViewModel> { CreateWordViewModelImpl(get(), get()) }
    viewModel<CategoriesViewModel> { CategoriesViewModelImpl(get()) }
    viewModel<WordListViewModel> { parameters -> WordListViewModelImpl(category = parameters.get(), get()) }
//    viewModel<WordListViewModel> { WordListViewModelImpl(get()) }

}