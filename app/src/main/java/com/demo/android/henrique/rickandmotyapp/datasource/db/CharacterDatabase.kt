package com.demo.android.henrique.rickandmotyapp.datasource.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.demo.android.henrique.rickandmotyapp.model.Character

@Database(entities = [Character::class], version = 1)
@TypeConverters(Converters::class)
abstract class CharacterDatabase: RoomDatabase() {

    abstract fun characterDao(): CharacterDao

    companion object {
        @Volatile
        private var instance: CharacterDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context): CharacterDatabase = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context): CharacterDatabase =
            Room.databaseBuilder(
                context.applicationContext,
                CharacterDatabase::class.java,
                "character_db.db"
            ).build()
    }
}