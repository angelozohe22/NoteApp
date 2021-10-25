package com.example.notesapp.data.source.mapper

import com.example.notesapp.data.entity.NoteEntity
import com.example.notesapp.domain.model.Note

class NotesEntityMapper {

    fun mapToObject(noteEntity : NoteEntity): Note =
        Note(id          = noteEntity.id,
            title        = noteEntity.title,
            description  = noteEntity.description)

    fun mapToEntity(note : Note): NoteEntity =
        NoteEntity(id          = note.id,
                   title       = note.title,
                   description = note.description)

}