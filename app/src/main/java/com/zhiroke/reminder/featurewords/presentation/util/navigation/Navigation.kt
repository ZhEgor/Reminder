package com.zhiroke.reminder.featurewords.presentation.util.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.pager.ExperimentalPagerApi
import com.zhiroke.reminder.featurewords.presentation.screens.categories.CategoriesScreen
import com.zhiroke.reminder.featurewords.presentation.screens.createword.CreateWordScreen
import com.zhiroke.reminder.featurewords.presentation.screens.wordlist.WordListScreen
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

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
        composable(
            route = Screen.WordListScreen.route + "/{categoryId}",
            arguments = listOf(
                navArgument("categoryId") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = false
                }
            )
        ) { entry ->
            WordListScreen(
                navController = navController,
                viewModel = getViewModel(parameters = { parametersOf(entry.arguments?.get("categoryId")) })
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
