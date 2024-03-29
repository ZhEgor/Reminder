package com.zhiroke.reminder.di.modules

import androidx.room.Room
import com.zhiroke.reminder.featurewords.domain.const.db.WordDBConst
import com.zhiroke.reminder.featurewords.domain.repository.category.CategoryEditor
import com.zhiroke.reminder.featurewords.domain.repository.category.CategoryFetcher
import com.zhiroke.reminder.featurewords.domain.repository.word.WordEditor
import com.zhiroke.reminder.featurewords.domain.repository.word.WordFetcher
import com.zhiroke.reminder.featurewords.data.repository.room.CategoryRoomRepository
import com.zhiroke.reminder.featurewords.data.repository.room.WordRoomRepository
import com.zhiroke.reminder.featurewords.data.datasource.dao.CategoryDao
import com.zhiroke.reminder.featurewords.data.datasource.dao.WordDao
import com.zhiroke.reminder.featurewords.data.datasource.database.WordDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {

    factory {
        Room.databaseBuilder(androidContext(), WordDatabase::class.java, WordDBConst.DB_NAME)
            .build()
    }

    factory { provideWordDao(get()) }
    factory { provideCategoryDao(get()) }

    single { WordRoomRepository(get()) }
    single { CategoryRoomRepository(get()) }

    factory<WordEditor> { get<WordRoomRepository>() }
    factory<WordFetcher> { get<WordRoomRepository>() }
    factory<CategoryEditor> { get<CategoryRoomRepository>() }
    factory<CategoryFetcher> { get<CategoryRoomRepository>() }

}

fun provideWordDao(wordDatabase: WordDatabase): WordDao {
    return wordDatabase.getWordDao()
}

fun provideCategoryDao(wordDatabase: WordDatabase): CategoryDao {
    return wordDatabase.getCategoryDao()
}
