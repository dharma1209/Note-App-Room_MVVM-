package com.raj.noteapproomwithmvvm.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.raj.noteapproomwithmvvm.R
import com.raj.noteapproomwithmvvm.models.Notes

class NotesAdapter(private val context: Context, var noteList:List<Notes>, val updateItemClickListener: UpdateItemClickListener,
                   val deleteItemClickListener: DeleteItemClickListener
):RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.notes_list_items,parent,false)
        return NotesViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val currentItem = noteList[position]
        holder._noteTitle.text = currentItem.noteTitle
        holder._noteDesc.text = currentItem.noteDescription
        holder.edit_button.setOnClickListener {
            updateItemClickListener.updateItemClick(currentItem)
        }
        holder.delete_button.setOnClickListener {
            deleteItemClickListener.deleteItemClick(currentItem)
        }
    }

    override fun getItemCount(): Int {
        return noteList.size
    }
    inner class NotesViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val _noteTitle:TextView = itemView.findViewById(R.id.note_title)
        val _noteDesc: TextView = itemView.findViewById(R.id.note_description)
        val edit_button:ImageView = itemView.findViewById(R.id.edit_button)
        val delete_button:ImageView = itemView.findViewById(R.id.delete_button)
    }
    interface UpdateItemClickListener{
        fun updateItemClick(notes: Notes)
    }
    interface DeleteItemClickListener{
        fun deleteItemClick(notes: Notes)
    }
}