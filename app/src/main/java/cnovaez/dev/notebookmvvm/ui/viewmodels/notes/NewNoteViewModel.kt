package cnovaez.dev.notebookmvvm.ui.viewmodels.notes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cnovaez.dev.notebookmvvm.domain.model.Note
import cnovaez.dev.notebookmvvm.domain.use_cases.notes.GetNoteByIdUseCase
import cnovaez.dev.notebookmvvm.domain.use_cases.notes.InsertNewNoteUseCase
import cnovaez.dev.notebookmvvm.utils.types.NoteActionType
import cnovaez.dev.notebookmvvm.utils.types.StateType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewNoteViewModel @Inject constructor(
    private val insertNewNoteUseCase: InsertNewNoteUseCase,
    private val getNoteByIdUseCase: GetNoteByIdUseCase
) :
    ViewModel() {

    val saving = MutableLiveData<StateType>()
    val noteModel = MutableLiveData<Note>()

    fun insertNote(note: Note, action: NoteActionType) {
        viewModelScope.launch {
            saving.postValue(StateType.WORKING)
            val response = insertNewNoteUseCase.invoke(note, action)
            saving.postValue(response)
        }
    }

    fun loadNote(noteId: Int) {
        viewModelScope.launch {
            val response = getNoteByIdUseCase.invoke(noteId)
            noteModel.postValue(response)
        }
    }

}