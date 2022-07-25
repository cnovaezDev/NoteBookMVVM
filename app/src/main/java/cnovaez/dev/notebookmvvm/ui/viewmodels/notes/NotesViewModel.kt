package cnovaez.dev.notebookmvvm.ui.viewmodels.notes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cnovaez.dev.notebookmvvm.domain.model.Note
import cnovaez.dev.notebookmvvm.domain.use_cases.notes.DeleteNoteUseCase
import cnovaez.dev.notebookmvvm.domain.use_cases.notes.GetAllNotesUseCase
import cnovaez.dev.notebookmvvm.domain.use_cases.notes.GetNoteByIdUseCase
import cnovaez.dev.notebookmvvm.domain.use_cases.notes.InsertNewNoteUseCase
import cnovaez.dev.notebookmvvm.utils.types.NoteActionType
import cnovaez.dev.notebookmvvm.utils.types.StateType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val getAllNotesUseCase: GetAllNotesUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val getNoteByIdUseCase: GetNoteByIdUseCase,
    private val insertNewNoteUseCase: InsertNewNoteUseCase
) : ViewModel() {

    val noteModel = MutableLiveData<List<Note>>()
    val singleNoteModel = MutableLiveData<Note>()
    val emptyNotes = MutableLiveData<Boolean>()
    val isLoading = MutableLiveData<Boolean>()
    val noteDelete = MutableLiveData<StateType>()


    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val response = getAllNotesUseCase.invoke();
            if (!response.isNullOrEmpty()) {
                emptyNotes.postValue(false)
                noteModel.postValue(response);
            } else {
                noteModel.postValue(emptyList());
                emptyNotes.postValue(true)

            }
            isLoading.postValue(false)
        }

    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            isLoading.postValue(true)
            var state = deleteNoteUseCase.invoke(note)
            noteDelete.postValue(state)
            //To reload the notes list
            onCreate()
            isLoading.postValue(false)
        }
    }

    fun loadNote(noteId: Int) {
        viewModelScope.launch {
            isLoading.postValue(true)
            val note = getNoteByIdUseCase.invoke(noteId)
            isLoading.postValue(false)
            singleNoteModel.postValue(note)
        }
    }

    fun insertNote(note: Note, action: NoteActionType) {
        viewModelScope.launch {
            insertNewNoteUseCase.invoke(note, action)
            onCreate()
        }
    }

}