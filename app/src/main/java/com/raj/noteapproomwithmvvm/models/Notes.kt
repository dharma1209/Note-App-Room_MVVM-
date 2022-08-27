package com.raj.noteapproomwithmvvm.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
data class Notes(
    val noteTitle:String,
    val noteDescription:String
) {
    @PrimaryKey(autoGenerate = true)
    var id:Long? = null
}
