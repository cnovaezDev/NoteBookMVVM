package cnovaez.dev.notebookmvvm.utils.types

import cnovaez.dev.notebookmvvm.utils.ext.strong

enum class NoteActionType {
    NEW, UPDATE, DELETE, SCHEDULE_NOTIFICATION;

    fun getDescription(title: String): String {
        return when (this) {
            NEW -> "New note '${strong(title)}' inserted"
            UPDATE -> "Note '${strong(title)}' updated"
            DELETE -> "Deleted note '${strong(title)}'"
            SCHEDULE_NOTIFICATION -> "Notification schedule for note '${strong(title)}'"
        }
    }
}