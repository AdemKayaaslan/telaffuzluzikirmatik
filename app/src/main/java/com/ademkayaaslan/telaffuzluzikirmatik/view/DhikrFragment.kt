package com.ademkayaaslan.telaffuzluzikirmatik.view

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.ademkayaaslan.telaffuzluzikirmatik.R
import com.ademkayaaslan.telaffuzluzikirmatik.adapter.ViewpagerAdapter
import com.ademkayaaslan.telaffuzluzikirmatik.model.ViewpagerItem
import com.ademkayaaslan.telaffuzluzikirmatik.viewmodel.DhikrViewModel
import kotlinx.android.synthetic.main.dhikr_fragment.*
import kotlinx.android.synthetic.main.home_fragment.*
import java.util.*
import kotlin.collections.ArrayList

class DhikrFragment : Fragment() {

    companion object {
        fun newInstance() = DhikrFragment()
    }


    var a0: Int = 0
    var b1: Int = 0
    var c2: Int = 0
    var d3: Int = 0
    var e4: Int = 0
    var f5: Int = 0
    var g6: Int = 0
    var h7: Int = 0
    var i8: Int = 0
    var j9: Int = 0
    var k10: Int = 0
    var positionInt = 0

    private lateinit var sliderItemList:ArrayList<ViewpagerItem>
    private lateinit var viewPagerAdapter: ViewpagerAdapter
    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var viewModel: DhikrViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dhikr_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DhikrViewModel::class.java)
        activity?.getSharedPreferences(
            "com.ademkayaaslan.telaffuzluzikirmatik",
            Context.MODE_PRIVATE
        )?.also { sharedPreferences = it }
        sliderItems()
        itemSliderView()
        virdButton()

        positionInt = sharedPreferences.getInt("positionInt",0)
        viewpager_dhikr.currentItem = positionInt

    }


    fun virdButton() {

        virdButton.setOnClickListener {
            virdCounter()
        }

    }

    fun virdCounter() {
        if (positionInt == 0) {
            a0 = a0.plus(1)
            textView.text = "vird :" + a0
        } else if (positionInt == 1) {
            b1 = b1.plus(1)
            textView.text = "vird :" + b1
        } else if (positionInt == 2) {
            c2 = c2.plus(1)
            textView.text = "vird :" + c2
        } else if (positionInt == 3) {
            d3 = d3.plus(1)
            textView.text = "vird :" + d3
        } else if (positionInt == 4) {
            e4 = e4.plus(1)
            textView.text = "vird :" + e4
        } else if (positionInt == 5) {
            f5 = f5.plus(1)
            textView.text = "vird :" + f5
        } else if (positionInt == 6) {
            g6 = g6.plus(1)
            textView.text = "vird :" + g6
        } else if (positionInt == 7) {
            h7 = h7.plus(1)
            textView.text = "vird :" + h7
        } else if (positionInt == 8) {
            i8 = i8.plus(1)
            textView.text = "vird :" + i8
        } else if (positionInt == 9) {
            j9 = j9.plus(1)
            textView.text = "vird :" + j9
        } else if (positionInt == 10) {
            k10 = k10.plus(1)
            textView.text = "vird :" + k10
        }


    }

    override fun onStop() {
        super.onStop()

        val timestamp = Calendar.getInstance().timeInMillis
        if (a0>0) {
            viewModel.saveDhikr(a0,0,timestamp)
        }
        if (b1>0) {
            viewModel.saveDhikr(b1,1,timestamp)
        }
        if (c2>0) {
            viewModel.saveDhikr(c2,2,timestamp)
        }
        if (d3>0) {
            viewModel.saveDhikr(d3,3,timestamp)
        }
        if (e4>0) {
            viewModel.saveDhikr(e4,4, timestamp)
        }
        if (f5>0) {
            viewModel.saveDhikr(f5,5,timestamp)
        }
        if (g6>0) {
            viewModel.saveDhikr(g6,6,timestamp)
        }
        if (h7>0) {
            viewModel.saveDhikr(h7,7,timestamp)
        }
        if (i8>0) {
            viewModel.saveDhikr(i8,8,timestamp)
        }
        if (j9>0) {
            viewModel.saveDhikr(j9,9,timestamp)
        }
        if (k10>0) {
            viewModel.saveDhikr(k10,10,timestamp)
        }
        sharedPreferences?.edit()?.putInt("positionInt", viewpager_dhikr_home.currentItem)?.apply()

    }





    private fun sliderItems() {
        sliderItemList = ArrayList()
        viewPagerAdapter = ViewpagerAdapter(viewpager_dhikr,sliderItemList)
        viewpager_dhikr.adapter = viewPagerAdapter
        viewpager_dhikr.clipToPadding = false
        viewpager_dhikr.clipChildren = false
        viewpager_dhikr.offscreenPageLimit = 1
        viewpager_dhikr.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER



        viewpager_dhikr.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    positionInt = position

                }
            }
        )
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