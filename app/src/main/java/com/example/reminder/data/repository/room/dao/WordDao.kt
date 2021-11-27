package com.example.reminder.data.repository.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.reminder.const.db.WordTableConst
import com.example.reminder.data.entity.Word
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addWord(word: Word)

    @Query("SELECT * FROM ${WordTableConst.TABLE_NAME} ORDER BY ${WordTableConst.CREATION_DATE} DESC")
    fun readAllWords(): Flow<List<Word>>

    @Query("DELETE FROM ${WordTableConst.TABLE_NAME} WHERE ${WordTableConst.SPELLING}=:spelling")
    fun deleteWord(spelling: String)

}