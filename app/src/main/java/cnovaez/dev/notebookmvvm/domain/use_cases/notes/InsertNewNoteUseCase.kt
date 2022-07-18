package cnovaez.dev.notebookmvvm.domain.use_cases.notes

import cnovaez.dev.notebookmvvm.data.repositories.NotesRepository
import cnovaez.dev.notebookmvvm.domain.model.Note
import cnovaez.dev.notebookmvvm.domain.model.toEntity
import cnovaez.dev.notebookmvvm.domain.model.toEntityWithId
import cnovaez.dev.notebookmvvm.utils.ext.errorMsg
import cnovaez.dev.notebookmvvm.utils.types.NoteActionType
import cnovaez.dev.notebookmvvm.utils.types.StateType
import javax.inject.Inject

class InsertNewNoteUseCase @Inject constructor(private val notesRepository: NotesRepository) {


    suspend operator fun invoke(note: Note, actionType: NoteActionType): StateType {
        var response = StateType.SUCCESS
        try {
            when(actionType){
                NoteActionType.NEW -> notesRepository.inserNoteDB(note.toEntity())
                NoteActionType.UPDATE -> notesRepository.inserNoteDB(note.toEntityWithId())
            }

        } catch (ex: Exception) {
            ex.errorMsg()
            response = StateType.FAIL
        }
        return response;
    }


}