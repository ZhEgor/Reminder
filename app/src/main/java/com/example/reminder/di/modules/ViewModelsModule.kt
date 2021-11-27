package com.example.reminder.di.modules

import com.example.reminder.ui.createWord.CreateWordViewModel
import com.example.reminder.ui.createWord.CreateWordViewModelImpl
import com.example.reminder.ui.wordList.WordListViewModel
import com.example.reminder.ui.wordList.WordListViewModelImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {

    viewModel<CreateWordViewModel> { CreateWordViewModelImpl(get()) }
    viewModel<WordListViewModel> { WordListViewModelImpl(get()) }
//    viewModel<WordDetailsViewModel> { WordDetailsViewModelImpl(get()) }

}