package com.example.roomhw3

import android.util.Log
import androidx.lifecycle.LiveData

class PersonRepository(private val personDao: PersonDao) {

    val persons: LiveData<List<Person>> = personDao.getAllPersons()

    suspend fun insert(person: Person) {
        personDao.insert(person)
        Log.d("@@@", "Repository insertPerson ${person.name}")
    }

    suspend fun delete(person: Person) {
        personDao.delete(person)
    }
}