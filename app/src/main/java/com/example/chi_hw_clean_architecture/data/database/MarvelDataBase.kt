package com.example.chi_hw_clean_architecture.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.chi_hw_clean_architecture.data.model.Marvel
import kotlinx.coroutines.CoroutineScope

@Database(
    entities = [Marvel::class], version = 1, exportSchema = false
)
abstract class MarvelDataBase : RoomDatabase() {
    abstract val marvelDao: MarvelDao

    companion object {
        private const val DB_NAME = "MarvelDB"

        @Volatile
        private var instance: MarvelDataBase? = null

        fun getDataBase(
            context: Context
        ): MarvelDataBase {
            return instance ?: synchronized(this) {
                val newDB = Room.databaseBuilder(
                    context.applicationContext,
                    MarvelDataBase::class.java,
                    DB_NAME
                )
                    .build()
                instance = newDB

                newDB
            }
        }
    }
}