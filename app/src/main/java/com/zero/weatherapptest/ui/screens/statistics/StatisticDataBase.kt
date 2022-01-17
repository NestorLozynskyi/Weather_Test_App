package com.zero.weatherapptest.ui.screens.statistics

import androidx.room.DatabaseConfiguration
import androidx.room.InvalidationTracker
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.zero.weatherapptest.base.BaseDB
import com.zero.weatherapptest.data.dao.HistoryDao

/*class StatisticDataBase: BaseDB() {
    /*fun db(context: Context): BaseDB = Room.databaseBuilder(
        context,
        BaseDB::class.java, "database"
    ).build()*/
    override fun historyDao(): HistoryDao? {
        TODO("Not yet implemented")
    }

    override fun createOpenHelper(config: DatabaseConfiguration?): SupportSQLiteOpenHelper {
        TODO("Not yet implemented")
    }

    override fun createInvalidationTracker(): InvalidationTracker {
        TODO("Not yet implemented")
    }

    override fun clearAllTables() {
        TODO("Not yet implemented")
    }

}*/