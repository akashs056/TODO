package com.example.todo

import androidx.lifecycle.LiveData

class NotesRepo(private val notesDao:Dao) {

    val allNotes :LiveData<List<Note>> = notesDao.allNotes()

    suspend fun insert(note: Note){
        notesDao.insert(note)
    }
    suspend fun delete(note: Note){
        notesDao.delete(note)
    }
}