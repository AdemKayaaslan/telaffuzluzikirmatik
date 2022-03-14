package com.ademkayaaslan.telaffuzluzikirmatik.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.ademkayaaslan.telaffuzluzikirmatik.model.Dhikr
import com.ademkayaaslan.telaffuzluzikirmatik.service.DhikrDatabase
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) :BaseViewModel(application) {

    val tasks = MutableLiveData<List<Dhikr>>()
    val lastWeektasks = MutableLiveData<List<Dhikr>>()
    val allTasks = MutableLiveData<List<Dhikr>>()

    fun getallDhikrs (dhikrId:Int) {

        launch {
            val dao = DhikrDatabase(getApplication()).DhikrDao()
            val dhikrList = dao.getDhikrsByDhikrId(dhikrId)


            allTasks.value = dhikrList

        }

    }

}