package com.example.notesapp.data.source

import androidx.lifecycle.LiveData
import com.example.notesapp.data.entity.NoteEntity

interface INotesDataSource {

    interface ILocalNotesSource{
        suspend fun addNote(note: NoteEntity)
        suspend fun updateNote(note: NoteEntity)
        suspend fun deleteNote(note: NoteEntity)
        suspend fun getAllNote(): List<NoteEntity>
        suspend fun getNoteListByQuery(query: String): List<NoteEntity>
    }


}