package com.example.reminder.di.modules

import androidx.room.Room
import com.example.reminder.const.db.WordDBConst
import com.example.reminder.data.repository.WordEditor
import com.example.reminder.data.repository.WordFetcher
import com.example.reminder.data.repository.WordRepository
import com.example.reminder.data.repository.room.WordRoomDataSource
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
    factory { WordRoomDataSource(get()) }
    factory<WordEditor>(Qualifiers.db_editor) { get<WordRoomDataSource>() }
    factory<WordFetcher>(Qualifiers.db_fetcher) { get<WordRoomDataSource>() }
    factory { WordRepository(get(Qualifiers.db_editor), get(Qualifiers.db_fetcher)) }
    single<WordEditor> { get<WordRepository>() }
    single<WordFetcher> { get<WordRepository>() }

}

fun provideWordDao(wordDatabase: WordDatabase): WordDao {
    return wordDatabase.getWordDao()
}