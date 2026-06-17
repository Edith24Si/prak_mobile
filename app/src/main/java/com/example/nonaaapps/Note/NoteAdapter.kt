package com.example.nonaaapps.Note

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nonaaapps.data.entity.NoteEntity
import com.example.nonaaapps.databinding.ItemNoteBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class NoteAdapter(
    private val notes: List<NoteEntity>,
    private val fragment: NoteFragment
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    inner class NoteViewHolder(val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.binding.tvTitle.text = note.title
        holder.binding.tvContent.text = note.content

        holder.itemView.setOnClickListener {
            val intent = android.content.Intent(holder.itemView.context, NoteFormActivity::class.java).apply {
                putExtra("NOTE_ID", note.id)
                putExtra("NOTE_TITLE", note.title)
                putExtra("NOTE_CONTENT", note.content)
            }
            holder.itemView.context.startActivity(intent)
        }

        holder.binding.ivDelete.setOnClickListener {
            MaterialAlertDialogBuilder(holder.itemView.context)
                .setTitle("Delete Note")
                .setMessage("Are you sure you want to delete this note?")
                .setPositiveButton("Delete") { _, _ ->
                    fragment.deleteNote(note)
                }
                .setNegativeButton("Cancel", null)
                .show()
        }
    }

    override fun getItemCount(): Int = notes.size
}