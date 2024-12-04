package com.example.roomhw3

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Person::class], version = 1, exportSchema = false)
abstract class PersonsDataBase : RoomDatabase() {
    abstract fun getPersonDao(): PersonDao

    companion object {
        private var INSTANCE: PersonsDataBase? = null
        fun getDataBase(context: Context): PersonsDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PersonsDataBase::class.java,
                    "persons_database"
                ).build()
                INSTANCE = instance
                instance
            }

        }
    }

}