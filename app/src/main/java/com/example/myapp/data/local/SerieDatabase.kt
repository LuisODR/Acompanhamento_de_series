package com.example.myapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapp.data.model.SerieEntity

@Database(entities = [SerieEntity::class], version = 1, exportSchema = false)
abstract class SerieDatabase : RoomDatabase() {
    abstract fun serieDao(): SerieDao

    companion object {
        @Volatile
        private var INSTANCE: SerieDatabase? = null

        fun getInstance(context: Context): SerieDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext, SerieDatabase::class.java, "series_db"
                ).build().also { INSTANCE = it }
            }
        }
    }
}
