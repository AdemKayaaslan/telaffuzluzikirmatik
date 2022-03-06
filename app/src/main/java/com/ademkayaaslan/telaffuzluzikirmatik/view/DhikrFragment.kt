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
        sliderItemList.add(ViewpagerItem(getString(R.string.arabic_subhanallah),getString(R.string.name_subhanallah),getString(R.string.explanation_subhanallah), "“Sübhanallah demek mizanın yarısını doldurur. Elhamdülillah demek ise teraziyi doldurmuş olur. Allah\'tan başka gerçek ilah yoktur, sadece O vardır, diyen kimse ile Allah arasında hiçbir perde yoktur. Cennette kendisiyle beraber oluncaya kadar…” \n(Tirmizî, Daavat, 87)"))
        sliderItemList.add(ViewpagerItem("سبحان الله","LA İLAHE İLLALLAH","Allah noksanlardan münezzehtir", "“Sübhanallah demek mizanın yarısını doldurur. Elhamdülillah demek ise teraziyi doldurmuş olur. Allah\'tan başka gerçek ilah yoktur, sadece O vardır, diyen kimse ile Allah arasında hiçbir perde yoktur. Cennette kendisiyle beraber oluncaya kadar…” \n(Tirmizî, Daavat, 87)"))
        sliderItemList.add(ViewpagerItem("سبحان الله","SÜBHANALLAH3","Allah noksanlardan münezzehtir", "“Sübhanallah demek mizanın yarısını doldurur. Elhamdülillah demek ise teraziyi doldurmuş olur. Allah\'tan başka gerçek ilah yoktur, sadece O vardır, diyen kimse ile Allah arasında hiçbir perde yoktur. Cennette kendisiyle beraber oluncaya kadar…” \n(Tirmizî, Daavat, 87)"))
        sliderItemList.add(ViewpagerItem("سبحان الله","ELHAMDULİLLAH","Allah noksanlardan münezzehtir", "“Sübhanallah demek mizanın yarısını doldurur. Elhamdülillah demek ise teraziyi doldurmuş olur. Allah\'tan başka gerçek ilah yoktur, sadece O vardır, diyen kimse ile Allah arasında hiçbir perde yoktur. Cennette kendisiyle beraber oluncaya kadar…” \n(Tirmizî, Daavat, 87)"))
        sliderItemList.add(ViewpagerItem("سبحان الله","ALLAHUEKBER","Allah noksanlardan münezzehtir", "“Sübhanallah demek mizanın yarısını doldurur. Elhamdülillah demek ise teraziyi doldurmuş olur. Allah\'tan başka gerçek ilah yoktur, sadece O vardır, diyen kimse ile Allah arasında hiçbir perde yoktur. Cennette kendisiyle beraber oluncaya kadar…” \n(Tirmizî, Daavat, 87)"))
        sliderItemList.add(ViewpagerItem("سبحان الله","ALLAH HÜMME SALLİ ALA SEYYİDİNA MUHAMMEDİN VE ALA ALİ SEYYİDİNA MUHAMMED","Allah noksanlardan münezzehtir", "“Sübhanallah demek mizanın yarısını doldurur. Elhamdülillah demek ise teraziyi doldurmuş olur. Allah\'tan başka gerçek ilah yoktur, sadece O vardır, diyen kimse ile Allah arasında hiçbir perde yoktur. Cennette kendisiyle beraber oluncaya kadar…” \n(Tirmizî, Daavat, 87)"))
        sliderItemList.add(ViewpagerItem("سبحان الله","LA HAVLE VE LA KUVVETE İLLA BİLLAH","Allah noksanlardan münezzehtir", "“Sübhanallah demek mizanın yarısını doldurur. Elhamdülillah demek ise teraziyi doldurmuş olur. Allah\'tan başka gerçek ilah yoktur, sadece O vardır, diyen kimse ile Allah arasında hiçbir perde yoktur. Cennette kendisiyle beraber oluncaya kadar…” \n(Tirmizî, Daavat, 87)"))

    }



}