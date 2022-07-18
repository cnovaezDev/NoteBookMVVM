package cnovaez.dev.notebookmvvm.domain.model


import androidx.room.ColumnInfo
import cnovaez.dev.notebookmvvm.R
import cnovaez.dev.notebookmvvm.data.database.entities.NoteEntity
import cnovaez.dev.notebookmvvm.utils.types.ActionType
import cnovaez.dev.notebookmvvm.utils.types.PriorityTypes

data class Note(
    val id: Int = -1,
    val title: String,
    val description: String,
    val icon: Int,
    val date: String,
    val priority: PriorityTypes,
    var image_data: List<ByteArray>? = null,
    var voice_data: List<ByteArray>? = null,
    var text_data: List<String>? = null,
    var action: ActionType = ActionType.DETAILS
)

fun Note.color(): Int {
    return when (priority) {
        PriorityTypes.HIGH -> R.color.colorRed200
        PriorityTypes.MEDIUM -> R.color.colorYellow200
        PriorityTypes.LOW -> R.color.Blue200
    }
}

fun Note.imgPriority(): Int {
    return when (priority) {
        PriorityTypes.HIGH -> R.drawable.prio_high
        PriorityTypes.MEDIUM -> R.drawable.prio_med
        PriorityTypes.LOW -> R.drawable.prio_low
    }
}


fun Note.toEntity() = NoteEntity(
    title = this.title,
    description = this.description,
    icon = this.icon,
    date = this.date,
    priority = this.priority
)

fun Note.toEntityWithId() = NoteEntity(
    id = this.id,
    title = this.title,
    description = this.description,
    icon = this.icon,
    date = this.date,
    priority = this.priority
)


