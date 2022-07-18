package cnovaez.dev.notebookmvvm.domain.use_cases

import cnovaez.dev.notebookmvvm.data.database.entities.toNoteModel
import cnovaez.dev.notebookmvvm.data.repositories.NotesRepository
import cnovaez.dev.notebookmvvm.domain.model.Note
import javax.inject.Inject

class GetNoteByIdUseCase @Inject constructor(
    private val notesRepository: NotesRepository
) {
    suspend operator fun invoke(noteId: Int): Note {
        return notesRepository.getNoteFromDB(noteId).toNoteModel()
    }
}