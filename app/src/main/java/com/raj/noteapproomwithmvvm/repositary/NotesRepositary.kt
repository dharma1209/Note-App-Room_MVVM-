package com.raj.noteapproomwithmvvm.repositary

import com.raj.noteapproomwithmvvm.database.NotesDatabase
import com.raj.noteapproomwithmvvm.models.Notes

class NotesRepositary(private val database: NotesDatabase) {

    suspend fun insertNote(notes: Notes) = database.getNotesDao().insertNotes(notes)
    suspend fun updateNote(notes: Notes) = database.getNotesDao().updateNotes(notes)
    suspend fun deleteNote(notes: Notes) = database.getNotesDao().deleteNotes(notes)

    fun getAllNotes() = database.getNotesDao().getAllNotes()
}