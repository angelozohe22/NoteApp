package com.example.notesapp.presentation.modules.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.R
import com.example.notesapp.common.baseAdapterDiffUtil
import com.example.notesapp.databinding.ItemNoteBinding
import com.example.notesapp.domain.model.Note
import kotlin.properties.Delegates

class NotesAdapter(
    private val listener: OnNoteItemClickListener
): RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    var notesList: List<Note> by baseAdapterDiffUtil(
        areItemsTheSame = { old, new -> old.id == new.id }
    )

    interface OnNoteItemClickListener{
        fun onNoteClicked(note: Note)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view =
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_note, parent, false)

        return NotesViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val note = notesList[position]
        holder.bindView(note)
    }

    override fun getItemCount(): Int = notesList.size

    inner class NotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemNoteBinding.bind(itemView)

        fun bindView(note: Note) {
            binding.apply {
                titleNote.text       = note.title
                descriptionNote.text = note.description
                cardContainer.setOnClickListener {
                    listener.onNoteClicked(note)
                }
            }
        }
    }
}