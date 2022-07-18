package cnovaez.dev.notebookmvvm.domain.use_cases.logs

import cnovaez.dev.notebookmvvm.data.database.entities.LogEntity
import cnovaez.dev.notebookmvvm.data.repositories.LogRepository
import javax.inject.Inject

class GetAllLogsUsesCase @Inject constructor(private val logRepository: LogRepository) {

    suspend operator fun invoke(): List<LogEntity> {
        return logRepository.getAllLogsFromDB()
    }
}