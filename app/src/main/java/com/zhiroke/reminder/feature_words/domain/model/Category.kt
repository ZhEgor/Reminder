package com.zhiroke.reminder.feature_words.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.zhiroke.reminder.feature_words.domain.const.db.CategoryTableConst
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = CategoryTableConst.TABLE_NAME)
data class Category(
    @PrimaryKey
    @ColumnInfo(name = CategoryTableConst.ID)
    val id: String,
    @ColumnInfo(name = CategoryTableConst.NAME)
    val name: String,
    @ColumnInfo(name = CategoryTableConst.LANGUAGE)
    val language: String,
    @ColumnInfo(name = CategoryTableConst.LAST_INTERACTED_TIME)
    val lastInteractedTime: String
) : Parcelable
