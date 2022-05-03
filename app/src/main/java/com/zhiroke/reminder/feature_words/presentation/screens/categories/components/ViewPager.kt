package com.zhiroke.reminder.feature_words.presentation.screens.categories.components

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.LocalOverScrollConfiguration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.zhiroke.reminder.feature_words.domain.model.Category
import com.zhiroke.reminder.feature_words.presentation.screens.wordList.WordListScreen
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalFoundationApi::class, ExperimentalPagerApi::class)
@Composable
fun PagerContent(pagerState: PagerState, navController: NavController, categories: List<Category>) {

    CompositionLocalProvider(LocalOverScrollConfiguration provides null) {
        Box(modifier = Modifier.fillMaxSize()) {
            HorizontalPager(state = pagerState) { page ->
                val category = categories[page]
                Log.d("tag", "categoryScreen ${category.name}")

                ViewModelScoped(navController, category)
//                WordListScreen(
//                    navController = navController,
//                    viewModel = ViewModelProvider(
//                        LocalViewModelStoreOwner.current!!,
//                        object :  ViewModelProvider.Factory {
//                            override fun <T : ViewModel> create(modelClass: Class<T>): T {
//                                return modelClass.getConstructor(Category::class.java)
//                                    .newInstance(category)
//                            }
//                        }
//                    ).get(categories[page].id, WordListViewModel::class.java)
//                )
            }
        }
    }
}

@ExperimentalPagerApi
@Composable
fun TabRowWrapper(pagerState: PagerState, categories: List<Category>) {
    val scope = rememberCoroutineScope()

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        backgroundColor = Color.White,
        contentColor = Color.Blue,
        divider = {
            TabRowDefaults.Divider(
                thickness = 3.dp,
                color = Color.White
            )
        },
        indicator = {
            TabRowDefaults.Indicator(
                height = 3.dp,
                color = Color.White
            )
        }
    ) {
        categories.forEachIndexed { index, category ->
            val isSelectedPage = pagerState.currentPage == index
            Tab(
                text = {
                    Text(
                        text = category.name,
                        color = if (isSelectedPage) Color.Blue else Color.Black,
                        fontSize = MaterialTheme.typography.h6.fontSize
                    )
                },
                selected = isSelectedPage,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
                enabled = true
            )
        }
    }
}

@Composable
fun ViewModelScoped(navController: NavController, category: Category) {
    val viewModelStore by remember {
        mutableStateOf(ViewModelStore())
    }
    DisposableEffect(key1 = "1", effect = {
        onDispose {
            viewModelStore.clear()
        }
    })
    LocalViewModelStoreOwner provides ViewModelStoreOwner { viewModelStore }
    WordListScreen(
        navController = navController,
        viewModel = getViewModel(
            parameters = { parametersOf(category) },
            owner = LocalViewModelStoreOwner.current
        )
    )
}
