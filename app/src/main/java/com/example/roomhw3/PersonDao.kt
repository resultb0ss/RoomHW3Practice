package com.example.roomhw3

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PersonDao {

    @Insert
    suspend fun insert(person: Person)

    @Delete
    suspend fun delete(person: Person)

    @Query("SELECT * FROM persons_table ORDER BY id ASC")
    fun getAllPersons(): LiveData<List<Person>>

    @Query("DELETE FROM persons_table")
    fun deleteAll()


}