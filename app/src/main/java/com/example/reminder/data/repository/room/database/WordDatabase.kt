package com.example.reminder.data.repository.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.reminder.data.entity.Word
import com.example.reminder.data.repository.room.dao.WordDao

@Database(entities = [Word::class], version = 1, exportSchema = true)
abstract class WordDatabase : RoomDatabase() {

    abstract fun getWordDao(): WordDao

}