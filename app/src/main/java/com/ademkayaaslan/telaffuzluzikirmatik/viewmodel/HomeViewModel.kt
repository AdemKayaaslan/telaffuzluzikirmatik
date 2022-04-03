package com.ademkayaaslan.telaffuzluzikirmatik.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.ademkayaaslan.telaffuzluzikirmatik.model.Dhikr
import com.ademkayaaslan.telaffuzluzikirmatik.service.DhikrDatabase
import kotlinx.coroutines.launch
import java.util.*

class HomeViewModel(application: Application) :BaseViewModel(application) {

    val lastDhikr = MutableLiveData<Dhikr>()
    val lastWeekDhikrs = MutableLiveData<List<Dhikr>>()
    val lastMonthDhikrs = MutableLiveData<List<Dhikr>>()

    fun getMonthDhikrs (timestamp: Long, dhikrId:Int) {
        launch {
            val dao = DhikrDatabase(getApplication()).DhikrDao()
            val dhikrList = dao.getDhikrsBydhikrIdAndTimeStamp(timestamp,dhikrId)

            lastMonthDhikrs.value = dhikrList

        }
    }

    fun getWeekDhikrs (timestamp: Long, dhikrId:Int) {

        launch {
            val dao = DhikrDatabase(getApplication()).DhikrDao()
            val dhikrList = dao.getDhikrsBydhikrIdAndTimeStamp(timestamp,dhikrId)

              lastWeekDhikrs.value = dhikrList

        }

    }

    fun getLastDhikr (dhikrId:Int) {

        launch {
            val dao = DhikrDatabase(getApplication()).DhikrDao()
            val dhikrList = dao.getDhikrsByDhikrId(dhikrId)

            val dummyDhikr = Dhikr(-1,-1,-1)
           if (dhikrList.isNotEmpty()) {
               lastDhikr.value = dhikrList.last()
           } else {
               lastDhikr.value = dummyDhikr
           }

        }

    }

}