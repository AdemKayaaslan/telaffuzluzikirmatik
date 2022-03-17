package com.ademkayaaslan.telaffuzluzikirmatik.view

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

class DhikrFragment : Fragment() {

    companion object {
        fun newInstance() = DhikrFragment()
    }

    private lateinit var sliderItemList:ArrayList<ViewpagerItem>
    private lateinit var viewPagerAdapter: ViewpagerAdapter

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

        sliderItems()
        itemSliderView()


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

                }
            }
        )
    }

    override fun onPause() {
        super.onPause()

    }

    override fun onResume() {
        super.onResume()

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