package com.ademkayaaslan.telaffuzluzikirmatik.viewmodel

import android.app.Application
import com.ademkayaaslan.telaffuzluzikirmatik.model.Dhikr
import com.ademkayaaslan.telaffuzluzikirmatik.service.DhikrDatabase
import kotlinx.coroutines.launch

class DhikrViewModel(application: Application) :BaseViewModel(application) {

    fun saveDhikr (count:Int, dhikrId:Int, timestamp: Long) {

        launch {
            val dao = DhikrDatabase(getApplication()).DhikrDao()


            val dhikr = Dhikr(count, dhikrId, timestamp)



            dao.insert(dhikr)


        }
    }

}