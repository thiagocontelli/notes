package com.thiagocontelli.notes.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.thiagocontelli.notes.models.Note
import com.thiagocontelli.notes.databinding.NoteCardBinding

class NotesAdapter : Adapter<NotesAdapter.NotesViewHolder>() {

    private var notes: List<Note> = emptyList()

    fun setNotes(notes: List<Note>) {
        this.notes = notes
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(
            NoteCardBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.binding.noteTitle.text = notes[position].title
        holder.binding.noteContent.text = notes[position].content
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    class NotesViewHolder(var binding: NoteCardBinding) : ViewHolder(binding.root)
}