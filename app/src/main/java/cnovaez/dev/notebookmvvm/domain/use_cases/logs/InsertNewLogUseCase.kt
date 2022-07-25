package cnovaez.dev.notebookmvvm.domain.use_cases.logs

import cnovaez.dev.notebookmvvm.data.database.entities.LogEntity
import cnovaez.dev.notebookmvvm.data.repositories.LogRepository
import javax.inject.Inject

class InsertNewLogUseCase @Inject constructor(
    private val logsRepository: LogRepository
) {

    suspend operator fun invoke(logEntity: LogEntity) {
        logsRepository.insertNewLogEntry(logEntity)
    }

}