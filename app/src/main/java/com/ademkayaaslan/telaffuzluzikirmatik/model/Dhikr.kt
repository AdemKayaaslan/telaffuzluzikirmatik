package com.ademkayaaslan.telaffuzluzikirmatik.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Dhikr (

    @ColumnInfo(name="name")
    val dhikrName:String,

    @ColumnInfo(name="id")
    val dhikrId:Int,

    @ColumnInfo(name="date")
    val dhikrDate:String

) {
    @PrimaryKey(autoGenerate = true)
    var uuid:Int = 0
}