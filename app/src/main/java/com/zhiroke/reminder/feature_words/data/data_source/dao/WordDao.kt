package com.zhiroke.reminder.feature_words.data.data_source.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.zhiroke.reminder.feature_words.domain.const.db.WordTableConst
import com.zhiroke.reminder.feature_words.domain.model.Word
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addWord(word: Word)

    @Update
    fun updateWord(word: Word): Int

    @Query("SELECT * FROM ${WordTableConst.TABLE_NAME} ORDER BY ${WordTableConst.CREATION_DATE} DESC")
    fun readAllWords(): Flow<List<Word>>

    @Query("SELECT * FROM ${WordTableConst.TABLE_NAME} WHERE ${WordTableConst.CATEGORY_ID}=:categoryId ORDER BY ${WordTableConst.CREATION_DATE} DESC LIMIT :limit OFFSET :position")
    fun readWordsFromPosition(categoryId: String, position: Int, limit: Int): List<Word>

    @Delete
    fun deleteWords(words: List<Word>): Int
}