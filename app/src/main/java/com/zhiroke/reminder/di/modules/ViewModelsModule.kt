package com.zhiroke.reminder.di.modules

import com.zhiroke.reminder.ui.createWord.CreateWordViewModel
import com.zhiroke.reminder.ui.createWord.CreateWordViewModelImpl
import com.zhiroke.reminder.ui.wordList.WordListViewModel
import com.zhiroke.reminder.ui.wordList.WordListViewModelImpl
import com.zhiroke.reminder.ui.wordListHolder.WordListHolderViewModel
import com.zhiroke.reminder.ui.wordListHolder.WordListHolderViewModelImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {

    viewModel<CreateWordViewModel> { CreateWordViewModelImpl(get(), get(), get(), get(), get()) }
    viewModel<WordListHolderViewModel> { WordListHolderViewModelImpl(get(), get()) }
    viewModel<WordListViewModel> { parameters -> WordListViewModelImpl(categoryId = parameters.get(), get()) }
//    viewModel<WordDetailsViewModel> { WordDetailsViewModelImpl(get()) }

}