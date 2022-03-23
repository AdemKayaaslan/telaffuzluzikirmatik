package com.ademkayaaslan.telaffuzluzikirmatik.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.ademkayaaslan.telaffuzluzikirmatik.model.Dhikr
import com.ademkayaaslan.telaffuzluzikirmatik.service.DhikrDatabase
import kotlinx.coroutines.launch

class DhikrViewModel(application: Application) :BaseViewModel(application) {

    val allDhikrs = MutableLiveData<List<Dhikr>>()

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

}