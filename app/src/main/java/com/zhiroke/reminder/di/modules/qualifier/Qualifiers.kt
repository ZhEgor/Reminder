package com.zhiroke.reminder.di.modules.qualifier

import org.koin.core.qualifier.named

object Qualifiers {

    val db_editor = named("DB_EDITOR")
    val db_fetcher = named("DB_FETCHER")

}
