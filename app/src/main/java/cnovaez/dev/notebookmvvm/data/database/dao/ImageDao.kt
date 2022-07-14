package cnovaez.dev.notebookmvvm.data.database.dao

import androidx.room.*
import cnovaez.dev.notebookmvvm.data.database.entities.ImageEntity

@Dao
interface ImageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImage(imageEntity: ImageEntity)

    @Delete
    suspend fun deleteImage(imageEntity: ImageEntity)

    @Delete
    suspend fun deleteAllImages(noteEntities: List<ImageEntity>)

    @Query("Select * from images_table")
    suspend fun loadAllImages(): List<ImageEntity>

    @Query("Select * from images_table where id=:id")
    suspend fun loadImage(id: Int): ImageEntity

    @Query("Select * from images_table where note_id=:noteId")
    suspend fun loadImagesAssociatedToANote(noteId: Int): List<ImageEntity>

    @Query("Delete from images_table where note_id=:noteId")
    suspend fun deleteImagesAssociatedToANote(noteId: Int)
}