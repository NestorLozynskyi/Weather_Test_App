package com.zero.weatherapptest.base

import androidx.room.RoomDatabase
import androidx.room.Database
import com.zero.weatherapptest.data.dao.HistoryDao
import com.zero.weatherapptest.data.objects.History

@Database(entities = [History::class], version = 1, exportSchema = false)
abstract class BaseDB : RoomDatabase() {
    abstract fun historyDao(): HistoryDao?
    /*abstract val historyDao: HistoryDao

    companion object {

        @Volatile
        private var INSTANCE: BaseDB? = null

        fun getInstance(context: Context): BaseDB {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        BaseDB::class.java,
                        "passhash_db"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }*/
}