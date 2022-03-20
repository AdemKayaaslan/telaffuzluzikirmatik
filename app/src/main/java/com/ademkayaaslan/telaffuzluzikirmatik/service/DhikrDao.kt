package com.ademkayaaslan.telaffuzluzikirmatik.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.ademkayaaslan.telaffuzluzikirmatik.model.Dhikr

@Dao
interface DhikrDao {

    @Insert
    suspend fun insert(tasks:Dhikr):Long

    @Insert
    suspend fun insertAll(vararg tasks:Dhikr):List<Long>

    @Query("SELECT * FROM dhikr")
    suspend fun getAllDhikrs():List<Dhikr>

    @Query("SELECT * FROM dhikr WHERE id = :dhikrId")
    suspend fun getDhikrsByDhikrId(dhikrId:Int):List<Dhikr>

    @Query("SELECT * FROM dhikr WHERE timestamp > :dhikrtimestamp AND id = :dhikrId")
    suspend fun getDhikrsBydhikrIdAndTimeStamp(dhikrtimestamp:Long,dhikrId:Int):List<Dhikr>

    @Query("DELETE FROM dhikr")
    suspend fun deleteAllDhikrs()

    @Query ("DELETE FROM dhikr WHERE uuid = :dhikrId")
    suspend fun deleteDhikr(dhikrId:Int)

    @Update
    suspend fun updateDhikr(task :Dhikr)

    @Update
    suspend fun updateMultipleDhikrs(taskList: List<Dhikr>)

/*
    @Query("SELECT * FROM dhikr WHERE week = 0")
    suspend fun getLiveTasks():List<Dhikr>

    @Query("SELECT * FROM dhikr WHERE day = :taskName AND week = 0")
    suspend fun getTasksByDay(taskName:String):List<Dhikr>

    @Query("SELECT * FROM dhikr WHERE week > 0")
    suspend fun getStatisticsTasks():List<Dhikr>
*/

}