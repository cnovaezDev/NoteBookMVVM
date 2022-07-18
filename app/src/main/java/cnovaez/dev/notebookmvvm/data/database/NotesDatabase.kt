package cnovaez.dev.notebookmvvm.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import cnovaez.dev.notebookmvvm.data.database.dao.ImageDao
import cnovaez.dev.notebookmvvm.data.database.dao.NoteDao
import cnovaez.dev.notebookmvvm.data.database.dao.TextDao
import cnovaez.dev.notebookmvvm.data.database.dao.VoiceDao
import cnovaez.dev.notebookmvvm.data.database.entities.*

@Database(entities = [NoteEntity::class, ImageEntity::class, VoiceEntity::class, TextEntity::class, LogEntity::class], version = 1)
abstract class NotesDatabase: RoomDatabase() {

    abstract fun getNotesDao(): NoteDao

    abstract fun getImagesDao(): ImageDao

    abstract fun getVoicesDao(): VoiceDao

    abstract fun getTextDao(): TextDao

    abstract fun getLogDao(): LogEntity


}