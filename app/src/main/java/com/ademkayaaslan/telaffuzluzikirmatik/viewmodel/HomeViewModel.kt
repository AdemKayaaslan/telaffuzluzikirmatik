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

    fun getDhikrs (timestamp: Long, dhikrId:Int,liveDataId:Int) {

        launch {
            val dao = DhikrDatabase(getApplication()).DhikrDao()
            val dhikrList = dao.getDhikrsBydhikrIdAndTimeStamp(timestamp,dhikrId)


            when(liveDataId) {
                1 -> lastWeekDhikrs.value = dhikrList
                2 -> lastMonthDhikrs.value = dhikrList
            }


        }

    }

    fun getLastDhikr (dhikrId:Int) {

        launch {
            val dao = DhikrDatabase(getApplication()).DhikrDao()
            val dhikrList = dao.getDhikrsByDhikrId(dhikrId)
            /*

            for (dhikr in dhikrList) {
                if (liveDhikr.dhikrId == -1) {
                    liveDhikr = dhikr
                } else {
                    if (dhikr.timestamp > liveDhikr.timestamp) {
                        liveDhikr = dhikr
                    }
                }
            }
*/
            val dummyDhikr = Dhikr(-1,-1,-1)
           if (dhikrList.isNotEmpty()) {
               lastDhikr.value = dhikrList.last()
           } else {
               lastDhikr.value = dummyDhikr
           }


        }

    }

}