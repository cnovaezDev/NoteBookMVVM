package cnovaez.dev.notebookmvvm.data.repositories

import cnovaez.dev.notebookmvvm.data.database.dao.LogDao
import cnovaez.dev.notebookmvvm.data.database.entities.LogEntity
import javax.inject.Inject

class LogRepository @Inject constructor(
    private val logDao: LogDao
) {

    suspend fun getAllLogsFromDB(): List<LogEntity> = logDao.loadAllLogs()

    suspend fun insertNewLogEntry(logEntity: LogEntity) = logDao.insertLog(logEntity)

    suspend fun deleteLogEntry(logEntity: LogEntity) = logDao.deleteLog(logEntity)
}