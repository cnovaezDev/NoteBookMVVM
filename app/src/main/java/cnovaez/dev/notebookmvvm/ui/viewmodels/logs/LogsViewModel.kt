package cnovaez.dev.notebookmvvm.ui.viewmodels.logs

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cnovaez.dev.notebookmvvm.data.database.entities.LogEntity
import cnovaez.dev.notebookmvvm.data.database.entities.toModel
import cnovaez.dev.notebookmvvm.domain.model.Log
import cnovaez.dev.notebookmvvm.domain.use_cases.logs.DeleteLogUseCase
import cnovaez.dev.notebookmvvm.domain.use_cases.logs.GetAllLogsUseCase
import cnovaez.dev.notebookmvvm.domain.use_cases.logs.InsertNewLogUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogsViewModel @Inject constructor(
    private val getAllLogsUsesCase: GetAllLogsUseCase,
    private val deleteLogUseCase: DeleteLogUseCase,
    private val insertNewLogUseCase: InsertNewLogUseCase
) : ViewModel() {

    val logsModel = MutableLiveData<List<Log>>()
    val loading = MutableLiveData<Boolean>()

    fun getAllLogs() {
        viewModelScope.launch {
            loading.postValue(true)
            val response = getAllLogsUsesCase.invoke()
            if (!response.isNullOrEmpty()) {
                logsModel.postValue(response.map { it.toModel() })
            } else {
                logsModel.postValue(emptyList())
            }
            loading.postValue(false)
        }
    }

    fun deleteLogEntry(logEntity: LogEntity) {
        viewModelScope.launch {
            deleteLogUseCase.invoke(logEntity)
            getAllLogs()
        }
    }
    fun insertNewLog(logEntity: LogEntity) {
        viewModelScope.launch {
            insertNewLogUseCase.invoke(logEntity)
            getAllLogs()
        }
    }


}