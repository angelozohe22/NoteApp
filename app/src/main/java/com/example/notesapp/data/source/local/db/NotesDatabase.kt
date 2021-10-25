package com.example.notesapp.data.source.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notesapp.data.entity.NoteEntity

@Database(entities = [NoteEntity::class], version = 1)
abstract class NotesDatabase: RoomDatabase() {

    abstract fun getNotesDao(): NotesDao

    companion object {

        @Volatile
        private var instance: NotesDatabase? = null
        private const val DBNAME = "Notes.db"

        fun getInstance(ctx: Context): NotesDatabase =
            instance ?: synchronized(this){
                instance ?: createDatabase(ctx).also{ instance = it }
            }

        private fun createDatabase(ctx: Context): NotesDatabase =
            Room.databaseBuilder(
                ctx.applicationContext,
                NotesDatabase::class.java,
                DBNAME
            ).build()

        fun destroyInstance(){
            instance = null
        }

    }

}