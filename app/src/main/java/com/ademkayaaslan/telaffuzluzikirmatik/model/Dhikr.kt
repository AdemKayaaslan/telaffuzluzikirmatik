package com.ademkayaaslan.telaffuzluzikirmatik.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp


@Entity
data class Dhikr (

   @ColumnInfo(name="count")
    val dhikrCount:Int,

    @ColumnInfo(name="id")
    val dhikrId:Int,

    @ColumnInfo(name="timestamp")
    val timestamp:Long

) {
    @PrimaryKey(autoGenerate = true)
    var uuid:Int = 0
}