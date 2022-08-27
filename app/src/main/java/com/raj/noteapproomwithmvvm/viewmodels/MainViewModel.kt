package com.raj.noteapproomwithmvvm.viewmodels

import androidx.lifecycle.ViewModel
import com.raj.noteapproomwithmvvm.models.Notes
import com.raj.noteapproomwithmvvm.repositary.NotesRepositary
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainViewModel(private val repositary: NotesRepositary):ViewModel() {

    fun insertNote(notes: Notes) = GlobalScope.launch {
        repositary.insertNote(notes)
    }

    fun updateNote(notes: Notes) = GlobalScope.launch {
        repositary.updateNote(notes)
    }

    fun deleteNote(notes: Notes) = GlobalScope.launch {
        repositary.deleteNote(notes)
    }

    fun getAllNotes() = repositary.getAllNotes()
}