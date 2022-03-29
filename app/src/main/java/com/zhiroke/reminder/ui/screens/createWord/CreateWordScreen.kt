package com.zhiroke.reminder.ui.screens.createWord

import androidx.navigation.NavController
import com.zhiroke.reminder.navigation.NavigationComponentScreen
import com.zhiroke.reminder.ui.screens.wordListHolder.WordListHolderFragmentDirections

class CreateWordScreen(private val from: From) : NavigationComponentScreen {

    override fun execute(navigator: NavController) {
        when (from) {
            is From.WordListHolder -> {
                navigator.navigate(WordListHolderFragmentDirections.actionWordListHolderFragmentToCreateWordFragment())
            }
        }
    }

    sealed class From {
        object WordListHolder : From()
    }
}