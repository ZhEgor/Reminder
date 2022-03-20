package com.zhiroke.reminder.data.repository.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zhiroke.reminder.const.db.WordDBConst
import com.zhiroke.reminder.data.entity.Category
import com.zhiroke.reminder.data.entity.Word
import com.zhiroke.reminder.data.repository.room.dao.CategoryDao
import com.zhiroke.reminder.data.repository.room.dao.WordDao

@Database(
    entities = [Word::class, Category::class],
    version = WordDBConst.DATABASE_VERSION,
    exportSchema = true
)
abstract class WordDatabase : RoomDatabase() {

    abstract fun getWordDao(): WordDao

    abstract fun getCategoryDao(): CategoryDao

}