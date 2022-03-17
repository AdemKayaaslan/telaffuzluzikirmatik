package com.ademkayaaslan.telaffuzluzikirmatik.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.ademkayaaslan.telaffuzluzikirmatik.model.Dhikr
import com.ademkayaaslan.telaffuzluzikirmatik.service.DhikrDatabase
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class HomeViewModel(application: Application) :BaseViewModel(application) {

    val tasks = MutableLiveData<List<Dhikr>>()
    val lastWeektasks = MutableLiveData<List<Dhikr>>()
    val allTasks = MutableLiveData<List<Dhikr>>()

    fun getallDhikrs (dhikrId:Int) {

        launch {
            val dao = DhikrDatabase(getApplication()).DhikrDao()
            val dhikrList = dao.getDhikrsByDhikrId(dhikrId)

            val patternString = "yyyy-MM-dd'T'HH:mm:ss'Z'"
            val myCalendar = Calendar.getInstance()
            val sdf = SimpleDateFormat(patternString,Locale.getDefault())
            val date = sdf.format(myCalendar.time)
            val dhikr = Dhikr("subhan",33,1,date)
          //  dao.insert(dhikr)

            allTasks.value = dhikrList

        }

    }

}