package com.zhiroke.reminder.feature_words.presentation.util.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.zhiroke.reminder.feature_words.presentation.screens.categories.CategoriesScreen
import com.zhiroke.reminder.feature_words.presentation.screens.createWord.CreateWordScreen
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.CategoriesScreen.route
    ) {
        composable(route = Screen.CategoriesScreen.route) {
            CategoriesScreen(
                navController = navController,
                viewModel = getViewModel()
            )
        }
        composable(route = Screen.CreateWordScreen.route) {
            CreateWordScreen(
                navController = navController,
                viewModel = getViewModel()
            )
        }

//        }        composable(
//            route = Screen.WordListScreen.route + "/{name}",
//            arguments = listOf(
//                navArgument("categoryId") {
//                    type = NavType.StringType
//                }
//            )) { entry ->
//            entry.arguments?.getString("categoryId")?.let { categoryId ->
//                WordListScreen(
//                    categoryId = categoryId,
//                    navController = navController,
//                    viewModel = getViewModel()
//                )
//            }
//        }
    }
}
