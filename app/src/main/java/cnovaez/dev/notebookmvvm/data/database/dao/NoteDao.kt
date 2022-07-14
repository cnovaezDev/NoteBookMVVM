package cnovaez.dev.notebookmvvm.data.database.dao

import androidx.room.*
import cnovaez.dev.notebookmvvm.data.database.entities.NoteEntity

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(noteEntity: NoteEntity)

    @Delete
    suspend fun deleteNote(noteEntity: NoteEntity)

    @Delete
    suspend fun deleteAllNotes(noteEntities: List<NoteEntity>)

    @Query("Select * from notes_table")
    suspend fun loadAllNotes() : List<NoteEntity>

    @Query("Select * from notes_table where id=:id")
    suspend fun loadNote(id: Int): NoteEntity
}