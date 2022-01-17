package com.zero.weatherapptest.base

import androidx.room.RoomDatabase
import androidx.room.Database
import com.zero.weatherapptest.data.dao.HistoryDao
import com.zero.weatherapptest.data.objects.History

@Database(entities = [History::class], version = 1, exportSchema = false)
abstract class BaseDB : RoomDatabase() {
    abstract fun historyDao(): HistoryDao?
}