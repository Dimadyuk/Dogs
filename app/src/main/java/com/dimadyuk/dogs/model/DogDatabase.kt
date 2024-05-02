package com.dimadyuk.dogs.model

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DogBreed::class], version = 1)
abstract class DogDatabase : RoomDatabase() {
    abstract fun dogDao(): DogDao

    companion object {
        private const val DATABASE_NAME = "dogdatabase"
        @Volatile
        private var instance: DogDatabase? = null
        private val LOCK = Any()
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) = androidx.room.Room.databaseBuilder(
            context.applicationContext,
            DogDatabase::class.java,
            DATABASE_NAME
        ).build()
    }
}