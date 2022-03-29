package com.zhiroke.reminder.di.modules

import com.zhiroke.reminder.ui.screens.createWord.CreateWordViewModel
import com.zhiroke.reminder.ui.screens.createWord.CreateWordViewModelImpl
import com.zhiroke.reminder.ui.screens.wordList.WordListViewModel
import com.zhiroke.reminder.ui.screens.wordList.WordListViewModelImpl
import com.zhiroke.reminder.ui.screens.wordListHolder.WordListHolderViewModel
import com.zhiroke.reminder.ui.screens.wordListHolder.WordListHolderViewModelImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {

    viewModel<CreateWordViewModel> { CreateWordViewModelImpl(get(), get(), get(), get()) }
    viewModel<WordListHolderViewModel> { WordListHolderViewModelImpl(get(), get()) }
    viewModel<WordListViewModel> { parameters -> WordListViewModelImpl(categoryId = parameters.get(), get(), get(), get()) }

}