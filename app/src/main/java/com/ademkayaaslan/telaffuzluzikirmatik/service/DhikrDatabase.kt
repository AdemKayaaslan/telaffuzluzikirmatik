package com.ademkayaaslan.telaffuzluzikirmatik.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ademkayaaslan.telaffuzluzikirmatik.model.Dhikr

@Database(entities = arrayOf(Dhikr::class),version = 1)
abstract class DhikrDatabase: RoomDatabase() {

    abstract fun TaskDao():DhikrDao


    companion object {

        @Volatile private var instance : DhikrDatabase?=null

        private val lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            instance ?: makeDatabase(context).also {
                instance = it
            }
        }

        private fun makeDatabase(context:Context) = Room.databaseBuilder(
            context.applicationContext,DhikrDatabase::class.java,"dhikrdatabase"
        ).build()

    }

}