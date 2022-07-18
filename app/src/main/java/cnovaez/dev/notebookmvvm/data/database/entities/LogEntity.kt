package cnovaez.dev.notebookmvvm.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import cnovaez.dev.notebookmvvm.utils.types.NoteActionType

@Entity(tableName = "logs_table")
data class LogEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "noteId") val noteId: Int = -1,
    @ColumnInfo(name = "action_date") val action_date: String,
    @ColumnInfo(name = "action_type") val action_type: NoteActionType,
    @ColumnInfo(name = "action_desc") val action_desc: String,
    @ColumnInfo(name = "notification_date") val notification_date: String = ""
)