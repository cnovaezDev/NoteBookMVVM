package cnovaez.dev.notebookmvvm.domain.use_cases

import cnovaez.dev.notebookmvvm.data.repositories.ImagesRepository
import cnovaez.dev.notebookmvvm.data.repositories.NotesRepository
import cnovaez.dev.notebookmvvm.data.repositories.TextsRepository
import cnovaez.dev.notebookmvvm.data.repositories.VoicesRepository
import javax.inject.Inject

class GetAllNotesUseCase @Inject constructor(
    private val noteRepository: NotesRepository,
    private val imageRepository: ImagesRepository,
    private val voiceRepository: VoicesRepository,
    private val textRepository: TextsRepository
) {

//    suspend operator fun invoke(): List<Note> {
//        val note_response = noteRepository.getAllNotesFromDB()
//
//        val response = note_response.
//
//
//        return emptyList()
//    }

}