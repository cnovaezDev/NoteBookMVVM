package cnovaez.dev.notebookmvvm.data.di

import android.content.Context
import androidx.room.Room
import cnovaez.dev.notebookmvvm.data.database.NotesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val DATABASE_NAME = "notes_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, NotesDatabase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideNotesDao(db: NotesDatabase) = db.getNotesDao()

    @Singleton
    @Provides
    fun provideImagesDao(db: NotesDatabase) = db.getImagesDao()

    @Singleton
    @Provides
    fun provideVoicesDao(db: NotesDatabase) = db.getVoicesDao()

    @Singleton
    @Provides
    fun provideTextsDao(db: NotesDatabase) = db.getTextDao()

    @Singleton
    @Provides
    fun provideLogsDao(db: NotesDatabase) = db.getLogDao()
}
