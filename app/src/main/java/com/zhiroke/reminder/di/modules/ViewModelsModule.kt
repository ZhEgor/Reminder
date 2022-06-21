package com.zhiroke.reminder.di.modules

import com.zhiroke.reminder.featurewords.presentation.screens.categories.CategoriesViewModel
import com.zhiroke.reminder.featurewords.presentation.screens.categories.CategoriesViewModelImpl
import com.zhiroke.reminder.featurewords.presentation.screens.createword.CreateWordViewModel
import com.zhiroke.reminder.featurewords.presentation.screens.createword.CreateWordViewModelImpl
import com.zhiroke.reminder.featurewords.presentation.screens.wordlist.WordListViewModel
import com.zhiroke.reminder.featurewords.presentation.screens.wordlist.WordListViewModelImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {

    viewModel<CreateWordViewModel> { CreateWordViewModelImpl(get(), get()) }
    viewModel<CategoriesViewModel> { CategoriesViewModelImpl(get()) }
    viewModel<WordListViewModel> { parameters -> WordListViewModelImpl(category = parameters.get(), get()) }
//    viewModel<WordListViewModel> { WordListViewModelImpl(get()) }

}