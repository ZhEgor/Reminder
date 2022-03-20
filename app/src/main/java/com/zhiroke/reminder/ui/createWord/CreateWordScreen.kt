package com.zhiroke.reminder.ui.createWord

import androidx.navigation.NavController
import com.zhiroke.reminder.R
import com.zhiroke.reminder.navigation.NavigationComponentScreen
import timber.log.Timber

class CreateWordScreen : NavigationComponentScreen{

    override fun execute(navigator: NavController) {
        Timber.d("I was here!")
//        navigator.navigate(R.layout.fragment_create_word)
    }

}