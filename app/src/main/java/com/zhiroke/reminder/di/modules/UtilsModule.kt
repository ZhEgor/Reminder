package com.zhiroke.reminder.di.modules

import com.zhiroke.reminder.navigation.NavigationComponentRouter
import com.zhiroke.reminder.navigation.Router
import org.koin.dsl.module

val utilsModule = module {

    single<Router> { NavigationComponentRouter() }

}