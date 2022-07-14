package cnovaez.dev.notebookmvvm.data.repositories

import cnovaez.dev.notebookmvvm.data.database.dao.VoiceDao
import cnovaez.dev.notebookmvvm.data.database.entities.VoiceEntity
import javax.inject.Inject

class VoicesRepository @Inject constructor(
    private val voicesDao: VoiceDao
) {

     //TODO VoicesDAO
    suspend fun getAllVoicesFromDB() =voicesDao.loadAllVoices()

    suspend fun getVoiceFromDB(id: Int) =voicesDao.loadVoice(id)

    suspend fun getVoicesFromDBAssociatedToANote(noteId: Int) =
       voicesDao.loadVoicesAssociatedToANote(noteId)

    suspend fun inserVoiceDB(imageEntity: VoiceEntity) =voicesDao.insertVoice(imageEntity)

    suspend fun deleteVoiceDB(imageEntity: VoiceEntity) =voicesDao.deleteVoice(imageEntity)

    suspend fun deleteVoicesDBAssociatedToANote(noteId: Int) =
       voicesDao.deleteVoicesAssociatedToANote(noteId)

}