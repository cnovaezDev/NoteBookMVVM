package cnovaez.dev.notebookmvvm.domain.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import cnovaez.dev.notebookmvvm.data.database.entities.LogEntity
import cnovaez.dev.notebookmvvm.utils.types.LogActions
import cnovaez.dev.notebookmvvm.utils.types.NoteActionType

class Log(
    val id: Int = -1,
    val noteId: Int = -1,
    val action_date: String,
    val action_type: NoteActionType,
    val action_desc: String,
    val notification_date: String = "",
    var logAction: LogActions = LogActions.NONE
)

fun Log.toEntity() = LogEntity(id, noteId, action_date, action_type, action_desc, notification_date)