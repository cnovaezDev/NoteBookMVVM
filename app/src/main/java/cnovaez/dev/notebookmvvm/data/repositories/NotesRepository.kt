package cnovaez.dev.notebookmvvm.data.repositories

import cnovaez.dev.notebookmvvm.data.database.dao.NoteDao
import cnovaez.dev.notebookmvvm.data.database.entities.NoteEntity
import javax.inject.Inject

class NotesRepository @Inject constructor(
    private val notesDao: NoteDao
) {

    suspend fun getAllNotesFromDB(): List<NoteEntity> = notesDao.loadAllNotes()

    suspend fun getNoteFromDB(id: Int): NoteEntity = notesDao.loadNote(id)

    suspend fun inserNoteDB(noteEntity: NoteEntity) = notesDao.insertNote(noteEntity)

    suspend fun deleteNoteDB(noteEntity: NoteEntity) = notesDao.deleteNote(noteEntity)


}