package com.zhiroke.reminder.featurewords.data.datasource.dao

import androidx.room.*
import com.zhiroke.reminder.featurewords.domain.const.db.CategoryTableConst
import com.zhiroke.reminder.featurewords.domain.model.Category
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCategory(category: Category)

    @Update
    suspend fun updateCategory(category: Category)

    @Query("SELECT * FROM ${CategoryTableConst.TABLE_NAME} ORDER BY ${CategoryTableConst.LAST_INTERACTED_TIME} DESC")
    fun readAllCategories(): Flow<List<Category>>

    @Query("DELETE FROM ${CategoryTableConst.TABLE_NAME} WHERE ${CategoryTableConst.ID}=:id")
    suspend fun deleteCategory(id: String)
}
