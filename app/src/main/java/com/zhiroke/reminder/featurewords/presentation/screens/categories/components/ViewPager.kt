package com.zhiroke.reminder.featurewords.presentation.screens.categories.components

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.LocalOverScrollConfiguration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.zhiroke.reminder.featurewords.domain.model.Category
import com.zhiroke.reminder.featurewords.domain.usecase.word.WordUseCase
import com.zhiroke.reminder.featurewords.presentation.screens.wordlist.WordListScreen
import com.zhiroke.reminder.featurewords.presentation.screens.wordlist.WordListViewModelImpl
import org.koin.androidx.compose.inject

@OptIn(ExperimentalFoundationApi::class, ExperimentalPagerApi::class)
@Composable
fun PagerContent(pagerState: PagerState, navController: NavController, categories: List<Category>) {

    CompositionLocalProvider(LocalOverScrollConfiguration provides null) {
        HorizontalPager(
            modifier = Modifier.fillMaxSize(),
            state = pagerState
        ) { page ->
            val category = categories[page]
            val wordUseCase by inject<WordUseCase>()
            Log.d("tag", "recompose screen: ${category.name}")
            WordListScreen(
                navController = navController,
                viewModel = viewModel<WordListViewModelImpl>(
                    key = category.id,
                    factory = object : ViewModelProvider.Factory {
                        override fun <T : ViewModel> create(modelClass: Class<T>): T {
                            return modelClass.getConstructor(
                                Category::class.java,
                                WordUseCase::class.java
                            ).newInstance(category, wordUseCase)
                        }
                    }
                )
            )
        }
    }
}