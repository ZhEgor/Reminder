package com.zhiroke.reminder.feature_words.presentation.screens.categories

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.zhiroke.reminder.feature_words.presentation.screens.categories.components.PagerContent
import com.zhiroke.reminder.feature_words.presentation.screens.categories.components.RoundedIconButtonAdd
import com.zhiroke.reminder.feature_words.presentation.screens.categories.components.TabRowWrapper
import com.zhiroke.reminder.feature_words.presentation.screens.createWord.CreateWordScreen
import kotlinx.coroutines.launch
import timber.log.Timber

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterialApi::class)
@Composable
fun CategoriesScreen(
    navController: NavController,
    viewModel: CategoriesViewModel
) {
    val state = viewModel.state
    val coroutineScope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetContent = {
            Timber.d("sheetContent")
            CreateWordScreen(navController = navController)
//            navController.navigate(Screen.CreateWordScreen.route)
        },
        sheetShape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
    ) {
        Timber.d("content")
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            if (state.categories.isNotEmpty()) {
                val pagerState = rememberPagerState(state.categories.size)
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                ) {
                    TabRowWrapper(
                        pagerState = pagerState,
                        categories = state.categories
                    )
                    PagerContent(
                        pagerState = pagerState,
                        navController = navController,
                        categories = state.categories
                    )
                }
            }
            RoundedIconButtonAdd(
                modifier = Modifier
                    .size(80.dp)
                    .align(Alignment.BottomEnd)
                    .padding(8.dp),
                onClick = {
                    coroutineScope.launch {
                        if (!bottomSheetState.isVisible) {
                            bottomSheetState.animateTo(ModalBottomSheetValue.Expanded)
                        }
//                    navController.navigate(Screen.CreateWordScreen.route)
                    }
                }
            )
        }
    }
}


@Preview
@Composable
fun Preview() {
    RoundedIconButtonAdd(
        modifier = Modifier
            .size(70.dp)
            .padding(8.dp)
    )
}