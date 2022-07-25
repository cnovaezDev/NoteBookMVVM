package cnovaez.dev.notebookmvvm.ui.viewmodels.logs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cnovaez.dev.notebookmvvm.data.database.entities.LogEntity
import cnovaez.dev.notebookmvvm.domain.use_cases.logs.InsertNewLogUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScheduleNotificationViewModel @Inject constructor(
    private val insertNewLogUseCase: InsertNewLogUseCase
) : ViewModel() {

    fun recordSchedule(logEntity: LogEntity) {
        viewModelScope.launch {
            insertNewLogUseCase.invoke(logEntity)
        }
    }
}