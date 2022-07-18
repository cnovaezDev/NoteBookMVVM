package cnovaez.dev.notebookmvvm.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import cnovaez.dev.notebookmvvm.data.database.entities.LogEntity

@Dao
interface LogDao {

    @Insert
    suspend fun insertLog(logEntity: LogEntity)

    @Delete
    suspend fun deleteLog(logEntity: LogEntity)

    @Query("Select * from logs_table")
    suspend fun loadAllLogs(): List<LogEntity>

    @Query("Select TOP (50) from logs_table")
    suspend fun load50LastLogs(): List<LogEntity>
}