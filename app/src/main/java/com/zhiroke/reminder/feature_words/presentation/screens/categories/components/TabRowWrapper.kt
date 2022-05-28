package com.zhiroke.reminder.feature_words.presentation.screens.categories.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.zhiroke.reminder.feature_words.domain.model.Category
import kotlinx.coroutines.launch

@ExperimentalPagerApi
@Composable
fun TabRowWrapper(pagerState: PagerState, categories: List<Category>) {
    val scope = rememberCoroutineScope()

    ScrollableTabRow(
        selectedTabIndex = pagerState.currentPage,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        backgroundColor = MaterialTheme.colors.background,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage]),
                color = MaterialTheme.colors.primaryVariant
            )
        },
        divider = {
            TabRowDefaults.Divider(
                thickness = 3.dp,
                color = MaterialTheme.colors.background
            )
        }
    ) {
        categories.forEachIndexed { index, category ->
            val isSelectedPage = pagerState.currentPage == index
            Tab(
                text = {
                    Text(
                        text = category.name,
                        fontSize = MaterialTheme.typography.h6.fontSize
                    )
                },
                selected = isSelectedPage,
                selectedContentColor = MaterialTheme.colors.primaryVariant,
                unselectedContentColor = MaterialTheme.colors.onBackground,
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