package cnovaez.dev.notebookmvvm.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import cnovaez.dev.notebookmvvm.data.database.dao.ImageDao
import cnovaez.dev.notebookmvvm.data.database.dao.NoteDao
import cnovaez.dev.notebookmvvm.data.database.dao.TextDao
import cnovaez.dev.notebookmvvm.data.database.dao.VoiceDao
import cnovaez.dev.notebookmvvm.data.database.entities.ImageEntity
import cnovaez.dev.notebookmvvm.data.database.entities.NoteEntity
import cnovaez.dev.notebookmvvm.data.database.entities.TextEntity
import cnovaez.dev.notebookmvvm.data.database.entities.VoiceEntity

@Database(entities = [NoteEntity::class, ImageEntity::class, VoiceEntity::class, TextEntity::class], version = 1)
abstract class NotesDatabase: RoomDatabase() {

    abstract fun getNotesDao(): NoteDao

    abstract fun getImagesDao(): ImageDao

    abstract fun getVoicesDao(): VoiceDao

    abstract fun getTextDao(): TextDao


}