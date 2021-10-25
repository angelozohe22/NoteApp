package com.example.notesapp.domain.repository

import androidx.lifecycle.LiveData
import com.example.notesapp.data.entity.NoteEntity
import com.example.notesapp.domain.model.Note

interface NotesRepository {

    suspend fun addNote(note: Note)
    suspend fun updateNote(note: Note)
    suspend fun deleteNote(note: Note)
    suspend fun getAllNote(): List<Note>
    suspend fun getNoteListByQuery(query: String): List<Note>

}