package com.example.reminder.data.repository.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.reminder.const.db.CategoryTableConst
import com.example.reminder.const.db.WordDBConst
import com.example.reminder.data.entity.Category
import com.example.reminder.data.entity.Word
import com.example.reminder.data.repository.room.dao.CategoryDao
import com.example.reminder.data.repository.room.dao.WordDao

@Database(entities = [Word::class, Category::class], version = WordDBConst.DATABASE_VERSION, exportSchema = true)
abstract class WordDatabase : RoomDatabase() {

    abstract fun getWordDao(): WordDao

    abstract fun getCategoryDao(): CategoryDao

}