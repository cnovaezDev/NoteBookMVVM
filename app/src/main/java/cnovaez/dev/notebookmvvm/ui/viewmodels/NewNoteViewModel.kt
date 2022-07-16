package cnovaez.dev.notebookmvvm.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cnovaez.dev.notebookmvvm.domain.model.Note
import cnovaez.dev.notebookmvvm.domain.use_cases.InsertNewNoteUseCase
import cnovaez.dev.notebookmvvm.utils.types.StateType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewNoteViewModel @Inject constructor(private val insertNewNoteUseCase: InsertNewNoteUseCase) :
    ViewModel() {

    val saving = MutableLiveData<StateType>()

    fun insertNote(note: Note) {
        viewModelScope.launch {
            saving.postValue(StateType.WORKING)
            val response = insertNewNoteUseCase.invoke(note)
            saving.postValue(response)
        }
    }

}