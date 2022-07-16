package cnovaez.dev.notebookmvvm.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "images_table")
data class ImageEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "image_data") val image_data: ByteArray,
    @ColumnInfo(name = "note_id") val note_id: Int
)

fun ImageEntity.imageData() = image_data;
