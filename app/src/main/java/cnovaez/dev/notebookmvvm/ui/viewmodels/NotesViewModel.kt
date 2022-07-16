package cnovaez.dev.notebookmvvm.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cnovaez.dev.notebookmvvm.R
import cnovaez.dev.notebookmvvm.domain.model.Note
import cnovaez.dev.notebookmvvm.domain.use_cases.GetAllNotesUseCase
import cnovaez.dev.notebookmvvm.utils.types.PriorityTypes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val getAllNotesUseCase: GetAllNotesUseCase
) : ViewModel() {

    val noteModel = MutableLiveData<List<Note>>()
    val isLoading = MutableLiveData<Boolean>()

    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val response = getAllNotesUseCase.invoke();
            if (!response.isNullOrEmpty()) {
                noteModel.postValue(response);
            } else {
                noteModel.postValue(
                    listOf(
                        Note(
                            -1,
                            "Drink tea",
                            "Desc",
                            R.drawable.ic_te,
                            "21/12/21",
                            PriorityTypes.HIGH,
                            null,
                            null,
                            null
                        ),Note(
                            -1,
                            "I like cakes :)",
                            "Desc",
                            R.drawable.ic_cake,
                            "12/02/22",
                            PriorityTypes.MEDIUM,
                            null,
                            null,
                            null
                        ),Note(
                            -1,
                            "I don't like covid :(",
                            "Desc",
                            R.drawable.ic_coronavirus,
                            "11/07/22",
                            PriorityTypes.LOW,
                            null,
                            null,
                            null
                        ),
                    )
                )
            }
            isLoading.postValue(false)
        }

    }

}