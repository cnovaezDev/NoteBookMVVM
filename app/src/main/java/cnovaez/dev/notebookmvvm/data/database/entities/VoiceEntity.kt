package cnovaez.dev.notebookmvvm.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "voices_table")
data class VoiceEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "voice_data") val voice_data: ByteArray,
    @ColumnInfo(name = "note_id") val note_id: Int
)


fun VoiceEntity.voiceData() = voice_data;
