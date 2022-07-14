package cnovaez.dev.notebookmvvm.data.repositories

import cnovaez.dev.notebookmvvm.data.database.dao.TextDao
import cnovaez.dev.notebookmvvm.data.database.entities.TextEntity
import javax.inject.Inject

class TextsRepository @Inject constructor(
    private val textsDao: TextDao
) {

    suspend fun getAllTextsFromDB() = textsDao.loadAllTexts()

    suspend fun getTextFromDB(id: Int) = textsDao.loadText(id)

    suspend fun getTextsFromDBAssociatedToANote(noteId: Int) =
        textsDao.loadTextsAssociatedToANote(noteId)

    suspend fun inserTextDB(imageEntity: TextEntity) = textsDao.insertText(imageEntity)

    suspend fun deleteTextDB(imageEntity: TextEntity) = textsDao.deleteText(imageEntity)

    suspend fun deleteTextsDBAssociatedToANote(noteId: Int) =
        textsDao.deleteTextsAssociatedToANote(noteId)


}