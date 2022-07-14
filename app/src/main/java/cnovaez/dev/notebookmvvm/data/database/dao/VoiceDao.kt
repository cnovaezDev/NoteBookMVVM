package cnovaez.dev.notebookmvvm.data.database.dao

import androidx.room.*
import cnovaez.dev.notebookmvvm.data.database.entities.VoiceEntity

@Dao
interface VoiceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVoice(voiceEntity: VoiceEntity)

    @Delete
    suspend fun deleteVoice(voiceEntity: VoiceEntity)

    @Delete
    suspend fun deleteAllVoices(noteEntities: List<VoiceEntity>)

    @Query("Select * from voices_table")
    suspend fun loadAllVoices(): List<VoiceEntity>

    @Query("Select * from voices_table where id=:id")
    suspend fun loadVoice(id: Int): VoiceEntity

    @Query("Select * from voices_table where note_id=:noteId")
    suspend fun loadVoicesAssociatedToANote(noteId: Int): List<VoiceEntity>

    @Query("Delete from voices_table where note_id=:noteId")
    suspend fun deleteVoicesAssociatedToANote(noteId: Int)
}