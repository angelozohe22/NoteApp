package com.example.notesapp.data.interactor

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.notesapp.data.entity.NoteEntity
import com.example.notesapp.data.source.INotesDataSource
import com.example.notesapp.data.source.mapper.NotesEntityMapper
import com.example.notesapp.domain.model.Note
import com.example.notesapp.domain.repository.NotesRepository

class NotesInteractor(
    private val local: INotesDataSource.ILocalNotesSource
): NotesRepository {

    //Tengo que hacer que mande el parametro, el success y el error
    override suspend fun addNote(note: Note) {
        val entity = NotesEntityMapper().mapToEntity(note)
        local.addNote(entity)
    }

    override suspend fun updateNote(note: Note) {
        val entity = NotesEntityMapper().mapToEntity(note)
        local.updateNote(entity)
    }

    override suspend fun deleteNote(note: Note) {
        val entity = NotesEntityMapper().mapToEntity(note)
        local.deleteNote(entity)
    }

    override suspend fun getAllNote(): List<Note> {
        return local.getAllNote().map {
            NotesEntityMapper().mapToObject(it)
        }
    }

    override suspend fun getNoteListByQuery(query: String): List<Note> {
        return local.getNoteListByQuery(query).map {
            NotesEntityMapper().mapToObject(it)
        }
    }
}