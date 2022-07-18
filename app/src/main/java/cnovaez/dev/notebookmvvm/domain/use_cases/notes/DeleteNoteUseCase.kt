package cnovaez.dev.notebookmvvm.domain.use_cases.notes

import cnovaez.dev.notebookmvvm.data.repositories.NotesRepository
import cnovaez.dev.notebookmvvm.domain.model.Note
import cnovaez.dev.notebookmvvm.domain.model.toEntity
import cnovaez.dev.notebookmvvm.domain.model.toEntityWithId
import cnovaez.dev.notebookmvvm.utils.ext.errorMsg
import cnovaez.dev.notebookmvvm.utils.types.StateType
import javax.inject.Inject

class DeleteNoteUseCase @Inject constructor(private val notesRepository: NotesRepository) {

    suspend operator fun invoke(note: Note): StateType {
        var state = StateType.SUCCESS
        try {
            notesRepository.deleteNoteDB(note.toEntityWithId())
        } catch (ex: Exception) {
            ex.errorMsg()
            state = StateType.FAIL
        }
        return state
    }

}