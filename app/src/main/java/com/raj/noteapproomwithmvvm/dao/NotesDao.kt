package com.raj.noteapproomwithmvvm.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.raj.noteapproomwithmvvm.models.Notes

@Dao
interface NotesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNotes(notes: Notes)

    @Update
    suspend fun updateNotes(notes: Notes)

    @Delete
    suspend fun deleteNotes(notes: Notes)

    @Query("Select * from note_table order by id ASC")
    fun getAllNotes(): LiveData<List<Notes>>
}