package cnovaez.dev.notebookmvvm.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import cnovaez.dev.notebookmvvm.domain.model.Note
import cnovaez.dev.notebookmvvm.utils.types.ActionType
import cnovaez.dev.notebookmvvm.utils.types.PriorityTypes

@Entity(tableName = "notes_table")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "icon") val icon: Int,
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "priority") val priority: PriorityTypes
)

fun NoteEntity.toNoteModel() =
    Note(
        id = id,
        title = title,
        description = description,
        icon = icon,
        date = date,
        priority = priority,
        image_data = null,
        text_data = null,
        voice_data = null,
        action = ActionType.DETAILS
)
