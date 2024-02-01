package com.example.todo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    lateinit var allNotes :LiveData<List<Note>>
    val repo : NotesRepo
    init {
        val dao=NoteDatabase.getDatabase(application).getDao()
        repo=NotesRepo(dao)
        allNotes=repo.allNotes
    }
    fun deleteNote(note: Note) =viewModelScope.launch(Dispatchers.IO) {
        repo.delete(note)
    }
    fun addNote(note: Note) =viewModelScope.launch(Dispatchers.IO) {
        repo.insert(note)
    }
}