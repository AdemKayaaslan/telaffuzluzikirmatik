package com.ademkayaaslan.telaffuzluzikirmatik.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.ademkayaaslan.telaffuzluzikirmatik.model.Dhikr
import com.ademkayaaslan.telaffuzluzikirmatik.service.DhikrDatabase
import kotlinx.coroutines.launch

class DhikrViewModel(application: Application) :BaseViewModel(application) {

    val allDhikrs = MutableLiveData<List<Dhikr>>()
    val monthDhikrs = MutableLiveData<List<Dhikr>>()

    fun saveDhikr (count:Int, dhikrId:Int, timestamp: Long) {

        launch {
            val dao = DhikrDatabase(getApplication()).DhikrDao()

            val dhikr = Dhikr(count, dhikrId, timestamp)

            dao.insert(dhikr)

        }
    }

    fun getAllDhikrs () {

        launch {
            val dao = DhikrDatabase(getApplication()).DhikrDao()
            val dhikrList = dao.getAllDhikrs()

            allDhikrs.value = dhikrList

        }

    }

    fun getMonthDhikrs (timestamp: Long,dhikrId: Int) {

        launch {
            val dao = DhikrDatabase(getApplication()).DhikrDao()
            val dhikrList = dao.getDhikrsBydhikrIdAndTimeStamp(timestamp,dhikrId)

            monthDhikrs.value = dhikrList

        }

    }

    fun deleteDhikrById (dhikrId:Int) {

        launch {
            val dao = DhikrDatabase(getApplication()).DhikrDao()

            dao.deleteDhikr(dhikrId)

        }

    }

}