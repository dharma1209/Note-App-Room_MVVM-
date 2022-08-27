package com.raj.noteapproomwithmvvm

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.raj.noteapproomwithmvvm.adapters.NotesAdapter
import com.raj.noteapproomwithmvvm.database.NotesDatabase
import com.raj.noteapproomwithmvvm.models.Notes
import com.raj.noteapproomwithmvvm.repositary.NotesRepositary
import com.raj.noteapproomwithmvvm.viewmodels.MainViewModel
import com.raj.noteapproomwithmvvm.viewmodels.MainViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.add_note_dialog.*

class MainActivity : AppCompatActivity(), NotesAdapter.UpdateItemClickListener, NotesAdapter.DeleteItemClickListener {
    lateinit var noteList:List<Notes>
    lateinit var adapter: NotesAdapter
    lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        noteList = arrayListOf()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        adapter = NotesAdapter(this,noteList,this,this)
        recyclerView.adapter = adapter
        val notesRepositary = NotesRepositary(NotesDatabase.getDatabase(this))
        mainViewModel = ViewModelProvider(this, MainViewModelFactory(notesRepositary)).get(
            MainViewModel::class.java)
        mainViewModel.getAllNotes().observe(this, Observer {
            adapter.noteList = it
            adapter.notifyDataSetChanged()

        })
        floating_button.setOnClickListener {
            openDialog()
        }

    }

    private fun openDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.add_note_dialog)
        dialog.cancel_button.setOnClickListener {
            dialog.dismiss()
        }
        dialog.add_button.setOnClickListener {
            val title = dialog.edt_title.text.toString()
            val desc = dialog.edt_description.text.toString()
            if (title.isNotEmpty() && desc.isNotEmpty()){
                val note = Notes(title,desc)
                mainViewModel.insertNote(note)
                adapter.notifyDataSetChanged()
                Toast.makeText(this,"Note Added",Toast.LENGTH_SHORT).show()
                dialog.edt_title.setText("")
                dialog.edt_description.setText("")
            } else {
                Toast.makeText(this,"Please enter all detail",Toast.LENGTH_SHORT).show()
            }
        }
        dialog.show()
    }

    override fun updateItemClick(notes: Notes) {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.add_note_dialog)
        dialog.add_button.setText("Update")
        dialog.edt_title.setText(notes.noteTitle)
        dialog.edt_description.setText(notes.noteDescription)
        dialog.cancel_button.setOnClickListener {
            dialog.dismiss()
        }
        dialog.add_button.setOnClickListener {
            val title = dialog.edt_title.text.toString()
            val desc = dialog.edt_description.text.toString()
            if (title.isNotEmpty() && desc.isNotEmpty()){
                val note = Notes(title,desc)
                note.id = notes.id
                mainViewModel.updateNote(note)
                adapter.notifyDataSetChanged()
                Toast.makeText(this,"Item Updated",Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            } else {
                Toast.makeText(this,"Please enter all detail",Toast.LENGTH_SHORT).show()
            }
        }
        dialog.show()
    }

    override fun deleteItemClick(notes: Notes) {
        mainViewModel.deleteNote(notes)
        adapter.notifyDataSetChanged()
        Toast.makeText(this,"${notes.noteTitle} is deleted",Toast.LENGTH_SHORT).show()
    }
}