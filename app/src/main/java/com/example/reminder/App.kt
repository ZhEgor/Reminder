package com.example.reminder

import android.app.Application
import com.example.reminder.di.modules.dataModule
import com.example.reminder.di.modules.useCasesModule
import com.example.reminder.di.modules.utilsModule
import com.example.reminder.di.modules.viewModelsModule
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) Timber.plant(object : Timber.DebugTree() {
            override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
                super.log(priority, "TAG - $tag", message, t)
            }
        })

        startKoin {
            androidContext(this@App)
            modules(listOf(utilsModule, dataModule, useCasesModule, viewModelsModule))
        }

    }

}
