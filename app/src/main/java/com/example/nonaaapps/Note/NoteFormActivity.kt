package com.example.nonaaapps.Note

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.nonaaapps.data.AppDatabase
import com.example.nonaaapps.data.entity.NoteEntity
import com.example.nonaaapps.databinding.ActivityNoteFormBinding
import kotlinx.coroutines.launch

class NoteFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNoteFormBinding
    private lateinit var db: AppDatabase
    private var noteId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        noteId = intent.getIntExtra("NOTE_ID", 0)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = if (noteId == 0) "Add Note" else "Edit Note"
            setDisplayHomeAsUpEnabled(true)
        }
        binding.toolbar.setNavigationOnClickListener { finish() }

        db = AppDatabase.getInstance(this)

        if (noteId != 0) {
            binding.etTitle.setText(intent.getStringExtra("NOTE_TITLE"))
            binding.etContent.setText(intent.getStringExtra("NOTE_CONTENT"))
            binding.btnSaveNote.text = "Update"
        }

        binding.btnSaveNote.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val content = binding.etContent.text.toString()

            if (title.isNotBlank() && content.isNotBlank()) {
                lifecycleScope.launch {
                    if (noteId == 0) {
                        val note = NoteEntity(
                            title = title,
                            content = content,
                            createdAt = System.currentTimeMillis()
                        )
                        db.noteDao().insert(note)
                    } else {
                        val note = NoteEntity(
                            id = noteId,
                            title = title,
                            content = content,
                            createdAt = System.currentTimeMillis()
                        )
                        db.noteDao().update(note)
                    }
                    finish()
                }
            } else {
                Toast.makeText(this, "Isi semua kolom!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}