package com.example.bitfit

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface ExerciseDao {
    @Query("select * from exercise_data_table")
    fun getAll(): Flow<List<RecordEntity>>

    @Insert
    fun insert(exerciseRec: RecordEntity)
}