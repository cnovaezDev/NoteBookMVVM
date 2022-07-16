package cnovaez.dev.notebookmvvm.domain.model


import androidx.room.ColumnInfo
import cnovaez.dev.notebookmvvm.data.database.entities.NoteEntity
import cnovaez.dev.notebookmvvm.utils.types.PriorityTypes

data class Note(
    val id: Int,
    val title: String,
    val description: String,
    val icon: Int,
    val date: String,
    val priority: PriorityTypes,
    var image_data: List<ByteArray>?,
    var voice_data: List<ByteArray>?,
    var text_data: List<String>?
)

fun Note.color(): Int {
    return when (priority) {
        PriorityTypes.HIGH -> android.R.color.holo_red_dark
        PriorityTypes.MEDIUM -> android.R.color.holo_orange_light
        PriorityTypes.LOW -> android.R.color.holo_blue_dark
    }
}

fun Note.toEntity() = NoteEntity(
    title = this.title,
    description = this.description,
    icon = this.icon,
    date = this.date,
    priority = this.priority
)


