package com.zhiroke.reminder.feature_words.data.data_source.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zhiroke.reminder.feature_words.domain.const.db.WordDBConst
import com.zhiroke.reminder.feature_words.domain.model.Category
import com.zhiroke.reminder.feature_words.domain.model.Word
import com.zhiroke.reminder.feature_words.data.data_source.dao.CategoryDao
import com.zhiroke.reminder.feature_words.data.data_source.dao.WordDao

@Database(
    entities = [Word::class, Category::class],
    version = WordDBConst.DATABASE_VERSION,
    exportSchema = true
)
abstract class WordDatabase : RoomDatabase() {

    abstract fun getWordDao(): WordDao

    abstract fun getCategoryDao(): CategoryDao

}