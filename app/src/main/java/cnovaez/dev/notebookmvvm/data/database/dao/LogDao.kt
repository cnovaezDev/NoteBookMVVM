package cnovaez.dev.notebookmvvm.data.database.dao

import androidx.room.*
import cnovaez.dev.notebookmvvm.data.database.entities.LogEntity

@Dao
interface LogDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLog(logEntity: LogEntity)

    @Delete
    suspend fun deleteLog(logEntity: LogEntity)

    @Query("Select * from logs_table")
    suspend fun loadAllLogs(): List<LogEntity>

//    @Query("Select Top (50) from logs_table")
//    suspend fun load50LastLogs(): List<LogEntity>
}