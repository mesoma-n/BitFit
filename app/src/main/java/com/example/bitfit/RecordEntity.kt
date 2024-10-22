package com.example.bitfit

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercise_data_table")
data class RecordEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "exerciseName") val exerciseName: String?,
    @ColumnInfo(name = "exerciseCalorie") val exerciseCalorie: Int?,
    @ColumnInfo(name = "exerciseTime") val exerciseTime: Double?,



    )