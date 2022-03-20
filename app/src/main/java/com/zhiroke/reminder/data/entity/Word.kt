package com.zhiroke.reminder.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.zhiroke.reminder.const.db.WordTableConst

@Entity(tableName = WordTableConst.TABLE_NAME)
data class Word(
    @PrimaryKey
    @ColumnInfo(name = WordTableConst.ID)
    val id: String,
    @ColumnInfo(name = WordTableConst.SPELLING)
    val spelling: String,
    @ColumnInfo(name = WordTableConst.TRANSLATION)
    val translation: String,
    @ColumnInfo(name = WordTableConst.PRONUNCIATION)
    val pronunciation: String,
    @ColumnInfo(name = WordTableConst.CATEGORY_ID)
    val categoryId: String,
    @ColumnInfo(name = WordTableConst.CREATION_DATE)
    val creationDate: String,
    @ColumnInfo(name = WordTableConst.LAST_SHOW_DATE)
    val lastShowDate: String,
    @ColumnInfo(name = WordTableConst.COMPLEXITY)
    val complexity: Int,
    @ColumnInfo(name = WordTableConst.REPETITIONS_SHOWED)
    val repetitionsShowed: Int
)
