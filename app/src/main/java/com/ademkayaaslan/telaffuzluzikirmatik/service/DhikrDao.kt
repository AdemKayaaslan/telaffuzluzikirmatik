package com.ademkayaaslan.telaffuzluzikirmatik.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.ademkayaaslan.telaffuzluzikirmatik.model.Dhikr
import com.mustafaademkayaaslan.planfollowchallenger.model.Task

@Dao
interface DhikrDao {

    @Insert
    suspend fun insertAll(vararg tasks:Dhikr):List<Long>

    @Query("SELECT * FROM task")
    suspend fun getAllTasks():List<Dhikr>

    @Query("SELECT * FROM task WHERE week = 0")
    suspend fun getLiveTasks():List<Dhikr>

    @Query("SELECT * FROM task WHERE uuid = :taskId")
    suspend fun getTask(taskId:Int):Dhikr

    @Query("SELECT * FROM task WHERE week > 0")
    suspend fun getStatisticsTasks():List<Dhikr>

    @Query("DELETE FROM Dhikr")
    suspend fun deleteAllTasks()

    @Query("SELECT * FROM task WHERE day = :taskName AND week = 0")
    suspend fun getTasksByDay(taskName:String):List<Dhikr>

    @Query ("DELETE FROM task WHERE uuid = :taskId")
    suspend fun deleteTask(taskId:Int)

    @Update
    suspend fun updateTask(task :Dhikr)

    @Update
    suspend fun updateMultipleTasks(taskList: List<Dhikr>)




}