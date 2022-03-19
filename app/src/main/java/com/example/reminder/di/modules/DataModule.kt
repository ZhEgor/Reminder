package com.example.reminder.di.modules

import androidx.room.Room
import com.example.reminder.const.db.WordDBConst
import com.example.reminder.data.entity.Category
import com.example.reminder.data.repository.*
import com.example.reminder.data.repository.room.CategoryRoomDataSource
import com.example.reminder.data.repository.room.WordRoomDataSource
import com.example.reminder.data.repository.room.dao.CategoryDao
import com.example.reminder.data.repository.room.dao.WordDao
import com.example.reminder.data.repository.room.database.WordDatabase
import com.example.reminder.di.modules.qualifier.Qualifiers
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

//    factory { WordRepository(get(Qualifiers.db_editor), get(Qualifiers.db_fetcher)) }
//    factory { WordRepository(get(Qualifiers.db_editor), get(Qualifiers.db_fetcher)) }

//    single<WordEditor> { get<WordRepository>() }
//    single<WordFetcher> { get<WordRepository>() }
//    single<CategoryEditor> { get<CategoryRepository>() }
//    single<CategoryFetcher> { get<CategoryRepository>() }

}

fun provideWordDao(wordDatabase: WordDatabase): WordDao {
    return wordDatabase.getWordDao()
}

fun provideCategoryDao(wordDatabase: WordDatabase): CategoryDao {
    return wordDatabase.getCategoryDao()
}
