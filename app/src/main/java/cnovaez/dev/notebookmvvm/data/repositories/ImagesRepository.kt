package cnovaez.dev.notebookmvvm.data.repositories

import cnovaez.dev.notebookmvvm.data.database.dao.ImageDao
import cnovaez.dev.notebookmvvm.data.database.entities.ImageEntity
import javax.inject.Inject

class ImagesRepository @Inject constructor(
    private val imagesDao: ImageDao
) {

    //TODO ImagesDAO
    suspend fun getAllImagesFromDB() = imagesDao.loadAllImages()

    suspend fun getImageFromDB(id: Int) = imagesDao.loadImage(id)

    suspend fun getImagesFromDBAssociatedToANote(noteId: Int) =
        imagesDao.loadImagesAssociatedToANote(noteId)

    suspend fun inserImageDB(imageEntity: ImageEntity) = imagesDao.insertImage(imageEntity)

    suspend fun deleteImageDB(imageEntity: ImageEntity) = imagesDao.deleteImage(imageEntity)

    suspend fun deleteImagesDBAssociatedToANote(noteId: Int) =
        imagesDao.deleteImagesAssociatedToANote(noteId)

}