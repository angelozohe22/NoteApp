package com.example.notesapp.data.source.local.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.notesapp.data.entity.NoteEntity

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNote(note: NoteEntity)

    @Update
    suspend fun updateNote(note: NoteEntity)

    @Delete
    suspend fun deleteNote(note: NoteEntity)

    @Query("SELECT * FROM notes ORDER BY id DESC")
    suspend fun getAllNotes(): List<NoteEntity>

    @Query("SELECT * FROM notes WHERE title LIKE :query OR description LIKE :query ORDER BY id DESC")
    suspend fun getNoteListByQuery(query: String): List<NoteEntity>

}