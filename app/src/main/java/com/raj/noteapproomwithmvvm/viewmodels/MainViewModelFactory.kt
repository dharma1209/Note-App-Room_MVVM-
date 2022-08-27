package com.raj.noteapproomwithmvvm.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.raj.noteapproomwithmvvm.repositary.NotesRepositary

class MainViewModelFactory(private val repositary: NotesRepositary): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repositary) as T
    }
}