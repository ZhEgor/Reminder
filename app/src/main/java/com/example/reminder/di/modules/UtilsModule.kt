package com.example.reminder.di.modules

import com.example.reminder.navigation.NavigationComponentRouter
import com.example.reminder.navigation.Router
import org.koin.dsl.module

val utilsModule = module {

    single<Router> { NavigationComponentRouter() }

}