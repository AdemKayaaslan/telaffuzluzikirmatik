package com.ademkayaaslan.telaffuzluzikirmatik.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.ademkayaaslan.telaffuzluzikirmatik.R
import com.ademkayaaslan.telaffuzluzikirmatik.adapter.ViewpagerAdapter
import com.ademkayaaslan.telaffuzluzikirmatik.model.ViewpagerItem
import com.ademkayaaslan.telaffuzluzikirmatik.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.home_fragment.*
import kotlin.math.abs

class HomeFragment : Fragment() {

    private lateinit var sliderItemList:ArrayList<ViewpagerItem>
    private lateinit var viewPagerAdapter:ViewpagerAdapter
    private lateinit var sliderHandle:Handler
    private lateinit var sliderRun:Runnable



    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        sliderItems()
        itemSliderView()

    }

    private fun sliderItems() {
        sliderItemList = ArrayList()
        viewPagerAdapter = ViewpagerAdapter(dhikr_viewpager,sliderItemList)
        dhikr_viewpager.adapter = viewPagerAdapter
        dhikr_viewpager.clipToPadding = false
        dhikr_viewpager.clipChildren = false
        dhikr_viewpager.offscreenPageLimit = 3
        dhikr_viewpager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        dhikr_viewpager.setPadding(120,0,120,0);

        val comPosPageTarn = CompositePageTransformer()
        //comPosPageTarn.addTransformer(MarginPageTransformer(24))
        comPosPageTarn.addTransformer { page, position ->

            val r:Float = 1 - abs(position)

            page.scaleY = 0.85f + r * 0.15f
        }

        dhikr_viewpager.setPageTransformer(comPosPageTarn)
        sliderHandle = Handler()
        sliderRun = Runnable {
            dhikr_viewpager.currentItem = dhikr_viewpager.currentItem + 1
        }
        dhikr_viewpager.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)

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
        sliderItemList.add(ViewpagerItem("سبحان الله","Ya Allah","Allah noksanlardan münezzehtir", "“Sübhanallah demek mizanın yarısını doldurur. Elhamdülillah demek ise teraziyi doldurmuş olur. Allah’tan başka gerçek ilah yoktur, sadece O vardır, diyen kimse ile Allah arasında hiçbir perde yoktur. Cennette kendisiyle beraber oluncaya kadar…” \n(Tirmizî, Daavat, 87)"))
        sliderItemList.add(ViewpagerItem("سبحان الله","LA İLAHE İLLALLAH","Allah noksanlardan münezzehtir", "“Sübhanallah demek mizanın yarısını doldurur. Elhamdülillah demek ise teraziyi doldurmuş olur. Allah’tan başka gerçek ilah yoktur, sadece O vardır, diyen kimse ile Allah arasında hiçbir perde yoktur. Cennette kendisiyle beraber oluncaya kadar…” \n(Tirmizî, Daavat, 87)"))
        sliderItemList.add(ViewpagerItem("سبحان الله","SÜBHANALLAH3","Allah noksanlardan münezzehtir", "“Sübhanallah demek mizanın yarısını doldurur. Elhamdülillah demek ise teraziyi doldurmuş olur. Allah’tan başka gerçek ilah yoktur, sadece O vardır, diyen kimse ile Allah arasında hiçbir perde yoktur. Cennette kendisiyle beraber oluncaya kadar…” \n(Tirmizî, Daavat, 87)"))
        sliderItemList.add(ViewpagerItem("سبحان الله","ELHAMDULİLLAH","Allah noksanlardan münezzehtir", "“Sübhanallah demek mizanın yarısını doldurur. Elhamdülillah demek ise teraziyi doldurmuş olur. Allah’tan başka gerçek ilah yoktur, sadece O vardır, diyen kimse ile Allah arasında hiçbir perde yoktur. Cennette kendisiyle beraber oluncaya kadar…” \n(Tirmizî, Daavat, 87)"))
        sliderItemList.add(ViewpagerItem("سبحان الله","ALLAHUEKBER","Allah noksanlardan münezzehtir", "“Sübhanallah demek mizanın yarısını doldurur. Elhamdülillah demek ise teraziyi doldurmuş olur. Allah’tan başka gerçek ilah yoktur, sadece O vardır, diyen kimse ile Allah arasında hiçbir perde yoktur. Cennette kendisiyle beraber oluncaya kadar…” \n(Tirmizî, Daavat, 87)"))
        sliderItemList.add(ViewpagerItem("سبحان الله","ALLAH HÜMME SALLİ ALA SEYYİDİNA MUHAMMEDİN VE ALA ALİ SEYYİDİNA MUHAMMED","Allah noksanlardan münezzehtir", "“Sübhanallah demek mizanın yarısını doldurur. Elhamdülillah demek ise teraziyi doldurmuş olur. Allah’tan başka gerçek ilah yoktur, sadece O vardır, diyen kimse ile Allah arasında hiçbir perde yoktur. Cennette kendisiyle beraber oluncaya kadar…” \n(Tirmizî, Daavat, 87)"))
        sliderItemList.add(ViewpagerItem("سبحان الله","LA HAVLE VE LA KUVVETE İLLA BİLLAH","Allah noksanlardan münezzehtir", "“Sübhanallah demek mizanın yarısını doldurur. Elhamdülillah demek ise teraziyi doldurmuş olur. Allah’tan başka gerçek ilah yoktur, sadece O vardır, diyen kimse ile Allah arasında hiçbir perde yoktur. Cennette kendisiyle beraber oluncaya kadar…” \n(Tirmizî, Daavat, 87)"))

    }




}