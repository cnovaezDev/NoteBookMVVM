package cnovaez.dev.notebookmvvm.data.database.dao

import androidx.room.*
import cnovaez.dev.notebookmvvm.data.database.entities.TextEntity

@Dao
interface TextDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertText(textEntity: TextEntity)

    @Delete
    suspend fun deleteText(textEntity: TextEntity)

    @Delete
    suspend fun deleteAllTexts(noteEntities: List<TextEntity>)

    @Query("Select * from texts_table")
    suspend fun loadAllTexts(): List<TextEntity>

    @Query("Select * from texts_table where id=:id")
    suspend fun loadText(id: Int): TextEntity

    @Query("Select * from texts_table where note_id=:noteId")
    suspend fun loadTextsAssociatedToANote(noteId: Int): List<TextEntity>

    @Query("Delete from texts_table where note_id=:noteId")
    suspend fun deleteTextsAssociatedToANote(noteId: Int)
}