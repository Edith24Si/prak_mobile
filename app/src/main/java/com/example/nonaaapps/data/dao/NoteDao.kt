package com.example.nonaaapps.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.nonaaapps.data.entity.NoteEntity

@Dao
interface NoteDao {
    @Query("SELECT * FROM notes")
    suspend fun getAll(): List<NoteEntity>

    @Insert
    suspend fun insert(note: NoteEntity)

    @androidx.room.Update
    suspend fun update(note: NoteEntity)

    @Query("SELECT * FROM notes WHERE title LIKE '%' || :query || '%' OR content LIKE '%' || :query || '%'")
    suspend fun search(query: String): List<NoteEntity>

    @Delete
    suspend fun delete(note: NoteEntity)
}