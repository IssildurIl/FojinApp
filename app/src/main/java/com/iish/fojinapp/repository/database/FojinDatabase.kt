package com.iish.fojinapp.repository.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.iish.fojinapp.repository.cache.model.ReviewCacheEntity

@Database(entities = [ReviewCacheEntity::class], version = 1)
abstract class FojinDatabase: RoomDatabase() {

    abstract fun fojinDao(): FojinDao

    companion object{
        const val DATABASE_NAME: String = "fojin_db"
    }


}