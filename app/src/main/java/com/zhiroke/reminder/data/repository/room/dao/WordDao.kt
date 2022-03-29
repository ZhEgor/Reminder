package com.zhiroke.reminder.data.repository.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.zhiroke.reminder.const.db.WordTableConst
import com.zhiroke.reminder.data.entity.Word
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addWord(word: Word)

    @Update
    fun updateWord(word: Word): Int

    @Query("SELECT * FROM ${WordTableConst.TABLE_NAME} ORDER BY ${WordTableConst.CREATION_DATE} DESC")
    fun readAllWords(): Flow<List<Word>>

    @Query("SELECT * FROM ${WordTableConst.TABLE_NAME} ORDER BY ${WordTableConst.CREATION_DATE} DESC")
    fun readAllWordsLiveData(): LiveData<List<Word>>

    @Query("SELECT * FROM ${WordTableConst.TABLE_NAME} WHERE ${WordTableConst.CATEGORY_ID}=:categoryId ORDER BY ${WordTableConst.CREATION_DATE} DESC LIMIT :limit OFFSET :position")
    fun readWordsFromPosition(categoryId: String, position: Int, limit: Int): List<Word>

    @Delete
    fun deleteWords(words: List<Word>): Int
}