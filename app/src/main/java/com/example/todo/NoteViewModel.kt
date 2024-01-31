package com.example.todo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    lateinit var allNotes :LiveData<List<Note>>
    init {
        val dao=NoteDatabase.getDatabase(application).getDao()
        val repo=NotesRepo(dao)
        allNotes=repo.allNotes
    }
}