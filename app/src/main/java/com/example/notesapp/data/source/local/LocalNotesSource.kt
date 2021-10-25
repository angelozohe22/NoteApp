package com.example.notesapp.data.source.local

import androidx.lifecycle.LiveData
import com.example.notesapp.data.entity.NoteEntity
import com.example.notesapp.data.source.INotesDataSource
import com.example.notesapp.data.source.local.db.NotesDatabase

class LocalNotesSource(
    private val notesDatabase: NotesDatabase
): INotesDataSource.ILocalNotesSource {
    override suspend fun addNote(note: NoteEntity) {
        notesDatabase.getNotesDao().addNote(note)
    }

    override suspend fun updateNote(note: NoteEntity) {
        notesDatabase.getNotesDao().updateNote(note)
    }

    override suspend fun deleteNote(note: NoteEntity) {
        notesDatabase.getNotesDao().deleteNote(note)
    }

    override suspend fun getAllNote(): List<NoteEntity> {
       return notesDatabase.getNotesDao().getAllNotes()
    }

    override suspend fun getNoteListByQuery(query: String): List<NoteEntity> {
        return notesDatabase.getNotesDao().getNoteListByQuery(query)
    }
}