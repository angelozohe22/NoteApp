package com.example.notesapp.presentation.viewmodel

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.notesapp.domain.model.Note
import com.example.notesapp.domain.repository.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(
    private val repository: NotesRepository
): ViewModel() {

    fun addNote(note: Note)= viewModelScope.launch(Dispatchers.IO){
        repository.addNote(note)
    }

    fun updateNote(note: Note)= viewModelScope.launch(Dispatchers.IO){
        repository.updateNote(note)
    }

    fun deleteNote(note: Note)= viewModelScope.launch(Dispatchers.IO){
        repository.deleteNote(note)
    }

    fun getAllNotes() = liveData(Dispatchers.IO) {
        emit(repository.getAllNote())
    }

    fun getNoteListByQuery(query: String) = liveData(Dispatchers.IO) {
        emit(repository.getNoteListByQuery(query))
    }

}