package com.zero.weatherapptest.data.dao

import androidx.room.*
import com.zero.weatherapptest.data.objects.History

@Dao
interface HistoryDao {
    @get:Query("SELECT * FROM table_history")
    val all: List<History>

    /*@Query("SELECT * FROM table_history WHERE id = :id")
    fun getById(id: Long): History*/

    @Insert
    fun insert(history: History)

    /*@Update
    fun update(history: History)*/

    @Delete
    fun delete(history: History)
}