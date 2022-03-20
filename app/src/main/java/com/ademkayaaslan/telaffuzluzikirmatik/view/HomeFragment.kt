package com.ademkayaaslan.telaffuzluzikirmatik.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.ademkayaaslan.telaffuzluzikirmatik.R
import com.ademkayaaslan.telaffuzluzikirmatik.adapter.ViewpagerAdapter
import com.ademkayaaslan.telaffuzluzikirmatik.model.Dhikr
import com.ademkayaaslan.telaffuzluzikirmatik.model.ViewpagerItem
import com.ademkayaaslan.telaffuzluzikirmatik.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.home_fragment.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.abs

class HomeFragment : Fragment() {

    private lateinit var sliderItemList:ArrayList<ViewpagerItem>
    private lateinit var viewPagerAdapter:ViewpagerAdapter
    private lateinit var sliderHandle:Handler
    private lateinit var sliderRun:Runnable
    private lateinit var viewModel: HomeViewModel

    private lateinit var sharedPreferences: SharedPreferences



    var positionInt = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        activity?.getSharedPreferences(
            "com.ademkayaaslan.telaffuzluzikirmatik",
            Context.MODE_PRIVATE
        )?.also { sharedPreferences = it }


        sliderItems()
        itemSliderView()
        observeLiveData()
        buttonStart()

        viewpager_dhikr_home.currentItem = sharedPreferences.getInt("positionInt",0)




    }

    fun buttonStart() {

        button_start.setOnClickListener {
            val sharedPreferences = activity?.getSharedPreferences(
                "com.ademkayaaslan.telaffuzluzikirmatik",
                Context.MODE_PRIVATE
            )

            sharedPreferences?.edit()?.putInt("positionInt", viewpager_dhikr_home.currentItem)?.apply()
            val dhikrFragment = DhikrFragment()
            activity?.supportFragmentManager?.beginTransaction()?.add(R.id.layout_container, dhikrFragment, "dhikrFragment")?.commit()

        }

    }


    fun observeLiveData() {

        viewModel.lastDhikr.observe(viewLifecycleOwner, Observer {
            it?.let {

                if (it.dhikrId == -1) {
                    list_dhikr.text = ""+0
                } else {
                    list_dhikr.text = ""+it.dhikrCount
                }


            }
        })

        viewModel.lastWeekDhikrs.observe(viewLifecycleOwner, Observer {
            it?.let {

                var counter = 0
                for (dhikr in it) {
                    counter += dhikr.dhikrCount
                }
                week_dhikr.text = ""+counter

            }
        })

        viewModel.lastMonthDhikrs.observe(viewLifecycleOwner, Observer {
            it?.let {

                var counter = 0
                for (dhikr in it) {
                    counter += dhikr.dhikrCount
                }
                month_dhikr.text = ""+counter

            }
        })
    }

    fun callLiveDataFunctions(dhikrId:Int) {

        val weekCallendar = Calendar.getInstance()
        val monthCallendar = Calendar.getInstance()


        weekCallendar.set(Calendar.DAY_OF_WEEK,weekCallendar.firstDayOfWeek)
        weekCallendar.set(Calendar.HOUR_OF_DAY,0)
        weekCallendar.set(Calendar.MINUTE,0)
        weekCallendar.set(Calendar.SECOND,0)

        monthCallendar.set(Calendar.DAY_OF_MONTH,1)
        weekCallendar.set(Calendar.HOUR_OF_DAY,0)
        weekCallendar.set(Calendar.MINUTE,0)
        weekCallendar.set(Calendar.SECOND,0)

        viewModel.getDhikrs(weekCallendar.timeInMillis,dhikrId,1)
        viewModel.getDhikrs(monthCallendar.timeInMillis,dhikrId,2)
        viewModel.getLastDhikr(dhikrId)

    }

    private fun sliderItems() {
        sliderItemList = ArrayList()
        viewPagerAdapter = ViewpagerAdapter(viewpager_dhikr_home,sliderItemList)
        viewpager_dhikr_home.adapter = viewPagerAdapter
        viewpager_dhikr_home.clipToPadding = false
        viewpager_dhikr_home.clipChildren = false
        viewpager_dhikr_home.offscreenPageLimit = 3
        viewpager_dhikr_home.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        viewpager_dhikr_home.setPadding(120,0,120,0);

        val comPosPageTarn = CompositePageTransformer()
        //comPosPageTarn.addTransformer(MarginPageTransformer(24))
        comPosPageTarn.addTransformer { page, position ->

            val r:Float = 1 - abs(position)

            page.scaleY = 0.85f + r * 0.15f
        }

        viewpager_dhikr_home.setPageTransformer(comPosPageTarn)
        sliderHandle = Handler()
        sliderRun = Runnable {
            viewpager_dhikr_home.currentItem = viewpager_dhikr_home.currentItem + 1
        }
        viewpager_dhikr_home.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)

                    callLiveDataFunctions(position)

                    sliderHandle.removeCallbacks(sliderRun)
                    sliderHandle.postDelayed(sliderRun,6000)
                }
            }
        )
    }

    override fun onPause() {
        super.onPause()
        sliderHandle.removeCallbacks(sliderRun)

    }

    override fun onResume() {
        super.onResume()
        sliderHandle.postDelayed(sliderRun,6000)
    }

    private fun itemSliderView() {
        val viewPagerYaAllah = ViewpagerItem(getString(R.string.arabic_ya_allah),getString(R.string.name_ya_allah),getString(R.string.explanation_ya_allah),  getString(R.string.body_ya_allah))
        val viewPagerLailaheillallah = ViewpagerItem(getString(R.string.arabic_lailaheillallah),getString(R.string.name_lailaheillallah),getString(R.string.explanation_lailaheillallah),  getString(R.string.body_lailaheillallah))
        val viewPagerSubhanallah = ViewpagerItem(getString(R.string.arabic_subhanallah),getString(R.string.name_subhanallah),getString(R.string.explanation_subhanallah),  getString(R.string.body_subhanallah))

        val viewPagerAlhamdulillah = ViewpagerItem(getString(R.string.arabic_alhamdullilah),getString(R.string.name_alhamdullilah),getString(R.string.explanation_alhamdullilah),  getString(R.string.body_alhamdullilah))
        val viewPagerAllahuekber = ViewpagerItem(getString(R.string.arabic_allahuekber),getString(R.string.name_allahuekber),getString(R.string.explanation_allahuekber),  getString(R.string.body_allahuekber))
        val viewPagerSalawat = ViewpagerItem(getString(R.string.arabic_salawat),getString(R.string.name_salawat),getString(R.string.explanation_salawat),  getString(R.string.body_salawat))

        val viewPagerSubhanallahiVebihamdihi = ViewpagerItem(getString(R.string.arabic_subhanallahi_vebihamdihi),getString(R.string.name_subhanallahi_vebihamdihi),getString(R.string.explanation_subhanallahi_vebihamdihi),  getString(R.string.body_subhanallahi_vebihamdihi))
        val viewPagerLahavle = ViewpagerItem(getString(R.string.arabic_lahavle),getString(R.string.name_lahavle),getString(R.string.explanation_lahavle),  getString(R.string.body_lahavle))
        val viewPagerHasbunallahu = ViewpagerItem(getString(R.string.arabic_hasbunallahu),getString(R.string.name_hasbunallahu),getString(R.string.explanation_hasbunallahu),  getString(R.string.body_hasbunallahu))

        val viewPagerLailahe = ViewpagerItem(getString(R.string.arabic_lailahe),getString(R.string.name_lailahe),getString(R.string.explanation_lailahe),  getString(R.string.body_lailahe))
        val viewPagerSubhanallahiVelhamdulillahi = ViewpagerItem(getString(R.string.arabic_subhanallahi_velhamdulillahi),getString(R.string.name_subhanallahi_velhamdulillahi),getString(R.string.explanation_subhanallahi_velhamdulillahi),  getString(R.string.body_subhanallahi_velhamdulillahi))


        sliderItemList.add(viewPagerYaAllah)
        sliderItemList.add(viewPagerLailaheillallah)
        sliderItemList.add(viewPagerSubhanallah)

        sliderItemList.add(viewPagerAlhamdulillah)
        sliderItemList.add(viewPagerAllahuekber)
        sliderItemList.add(viewPagerSalawat)

        sliderItemList.add(viewPagerSubhanallahiVebihamdihi)
        sliderItemList.add(viewPagerLahavle)
        sliderItemList.add(viewPagerHasbunallahu)

        sliderItemList.add(viewPagerLailahe)
        sliderItemList.add(viewPagerSubhanallahiVelhamdulillahi)
    }




}