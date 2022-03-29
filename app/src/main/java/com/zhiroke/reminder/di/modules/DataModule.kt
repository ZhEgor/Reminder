package com.zhiroke.reminder.di.modules

import androidx.room.Room
import com.zhiroke.reminder.const.db.WordDBConst
import com.zhiroke.reminder.data.repository.CategoryEditor
import com.zhiroke.reminder.data.repository.CategoryFetcher
import com.zhiroke.reminder.data.repository.WordEditor
import com.zhiroke.reminder.data.repository.WordFetcher
import com.zhiroke.reminder.data.repository.room.CategoryRoomDataSource
import com.zhiroke.reminder.data.repository.room.WordRoomDataSource
import com.zhiroke.reminder.data.repository.room.dao.CategoryDao
import com.zhiroke.reminder.data.repository.room.dao.WordDao
import com.zhiroke.reminder.data.repository.room.database.WordDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {

    factory {
        Room.databaseBuilder(androidContext(), WordDatabase::class.java, WordDBConst.DB_NAME)
            .build()
    }

    factory { provideWordDao(get()) }
    factory { provideCategoryDao(get()) }

    single { WordRoomDataSource(get()) }
    single { CategoryRoomDataSource(get()) }

    factory<WordEditor> { get<WordRoomDataSource>() }
    factory<WordFetcher> { get<WordRoomDataSource>() }
    factory<CategoryEditor> { get<CategoryRoomDataSource>() }
    factory<CategoryFetcher> { get<CategoryRoomDataSource>() }

}

fun provideWordDao(wordDatabase: WordDatabase): WordDao {
    return wordDatabase.getWordDao()
}

fun provideCategoryDao(wordDatabase: WordDatabase): CategoryDao {
    return wordDatabase.getCategoryDao()
}
