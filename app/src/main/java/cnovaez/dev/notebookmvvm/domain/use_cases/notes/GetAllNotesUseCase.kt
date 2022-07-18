@file:Suppress("LocalVariableName")

package cnovaez.dev.notebookmvvm.domain.use_cases.notes

import cnovaez.dev.notebookmvvm.data.database.entities.imageData
import cnovaez.dev.notebookmvvm.data.database.entities.textData
import cnovaez.dev.notebookmvvm.data.database.entities.toNoteModel
import cnovaez.dev.notebookmvvm.data.database.entities.voiceData
import cnovaez.dev.notebookmvvm.data.repositories.ImagesRepository
import cnovaez.dev.notebookmvvm.data.repositories.NotesRepository
import cnovaez.dev.notebookmvvm.data.repositories.TextsRepository
import cnovaez.dev.notebookmvvm.data.repositories.VoicesRepository
import cnovaez.dev.notebookmvvm.domain.model.Note
import javax.inject.Inject

class GetAllNotesUseCase @Inject constructor(
    private val noteRepository: NotesRepository,
    private val imageRepository: ImagesRepository,
    private val voiceRepository: VoicesRepository,
    private val textRepository: TextsRepository
) {

    suspend operator fun invoke(): List<Note> {
        val note_response = noteRepository.getAllNotesFromDB()

        val notes = note_response.map { it.toNoteModel() }

        notes.forEach { note ->
            note.image_data =
                imageRepository.getImagesFromDBAssociatedToANote(note.id).map { it.imageData() };
            note.voice_data =
                voiceRepository.getVoicesFromDBAssociatedToANote(note.id).map { it.voiceData() };
            note.text_data =
                textRepository.getTextsFromDBAssociatedToANote(note.id).map { it.textData() };
        }

        return notes
    }

}