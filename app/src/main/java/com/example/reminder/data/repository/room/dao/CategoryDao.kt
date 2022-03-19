package com.example.reminder.data.repository.room.dao

import androidx.room.*
import com.example.reminder.const.db.CategoryTableConst
import com.example.reminder.data.entity.Category
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addCategory(category: Category)

    @Update
    fun updateCategory(category: Category)

    @Query("SELECT * FROM ${CategoryTableConst.TABLE_NAME} ORDER BY ${CategoryTableConst.LAST_VIEWED_TIME} DESC")
    fun readAllCategories(): Flow<List<Category>>

    @Query("DELETE FROM ${CategoryTableConst.TABLE_NAME} WHERE ${CategoryTableConst.ID}=:id")
    fun deleteCategory(id: String)
}
