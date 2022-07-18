package cnovaez.dev.notebookmvvm.domain.use_cases

import cnovaez.dev.notebookmvvm.data.repositories.NotesRepository
import cnovaez.dev.notebookmvvm.domain.model.Note
import cnovaez.dev.notebookmvvm.domain.model.toEntity
import cnovaez.dev.notebookmvvm.utils.ext.errorMsg
import cnovaez.dev.notebookmvvm.utils.types.StateType
import javax.inject.Inject

class InsertNewNoteUseCase @Inject constructor(private val notesRepository: NotesRepository) {


    suspend operator fun invoke(note: Note): StateType {
        var response = StateType.SUCCESS
        try {
            notesRepository.inserNoteDB(note.toEntity())
        } catch (ex: Exception) {
            ex.errorMsg()
            response = StateType.FAIL
        }
        return response;
    }


}