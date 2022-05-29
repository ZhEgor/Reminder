package com.zhiroke.reminder.featurewords.data.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zhiroke.reminder.featurewords.domain.const.db.WordDBConst
import com.zhiroke.reminder.featurewords.domain.model.Category
import com.zhiroke.reminder.featurewords.domain.model.Word
import com.zhiroke.reminder.featurewords.data.datasource.dao.CategoryDao
import com.zhiroke.reminder.featurewords.data.datasource.dao.WordDao

@Database(
    entities = [Word::class, Category::class],
    version = WordDBConst.DATABASE_VERSION,
    exportSchema = true
)
abstract class WordDatabase : RoomDatabase() {

    abstract fun getWordDao(): WordDao

    abstract fun getCategoryDao(): CategoryDao

}