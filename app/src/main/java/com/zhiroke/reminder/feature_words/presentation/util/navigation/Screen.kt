package com.zhiroke.reminder.feature_words.presentation.util.navigation

sealed class Screen(val route: String) {

    object WordListScreen : Screen("word_list_screen")
    object CategoriesScreen : Screen("categories_screen")
    object CreateWordScreen : Screen("create_word_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { args ->
                append("/$args")
            }
        }
    }
}
