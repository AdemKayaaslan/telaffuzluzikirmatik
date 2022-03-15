package com.ademkayaaslan.telaffuzluzikirmatik.view

import android.content.Context
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
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.abs

class HomeFragment : Fragment() {

    private lateinit var sliderItemList:ArrayList<ViewpagerItem>
    private lateinit var viewPagerAdapter:ViewpagerAdapter
    private lateinit var sliderHandle:Handler
    private lateinit var sliderRun:Runnable
    val allDhikrs  = ArrayList<Dhikr>()
    val patternString = "yyyy-MM-dd'T'HH:mm:ss'Z'"



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


        viewModel.getallDhikrs(1)





        sliderItems()
        itemSliderView()
        observeLiveData()

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
        viewModel.allTasks.observe(viewLifecycleOwner, Observer {
            it?.let {
                allDhikrs.addAll(it)
                month_dhikr.text = ""+it.size
                setThisWeekDhikr()
            }
        })
    }

    fun setThisWeekDhikr() {

        val myCallendar = Calendar.getInstance()
        val thisWeekDhikr = ArrayList<Dhikr>()

        myCallendar.set(Calendar.DAY_OF_WEEK,myCallendar.firstDayOfWeek)


        val sdf = SimpleDateFormat(patternString,Locale.getDefault())

        try {
            for (dhikr in allDhikrs) {

            val date = sdf.parse(dhikr.dhikrDate)

            if (date != null) {
                if (myCallendar.time.before(date)) {
                    thisWeekDhikr.add(dhikr)
                }
            }

            }
            week_dhikr.text = ""+thisWeekDhikr.size
        } catch (e:Exception) {
            e.printStackTrace()
        }






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
        sliderItemList.add(ViewpagerItem(getString(R.string.arabic_subhanallah),getString(R.string.name_subhanallah),getString(R.string.explanation_subhanallah), "“Sübhanallah demek mizanın yarısını doldurur. Elhamdülillah demek ise teraziyi doldurmuş olur. Allah\'tan başka gerçek ilah yoktur, sadece O vardır, diyen kimse ile Allah arasında hiçbir perde yoktur. Cennette kendisiyle beraber oluncaya kadar…” \n(Tirmizî, Daavat, 87)"))
        sliderItemList.add(ViewpagerItem("سبحان الله","LA İLAHE İLLALLAH","Allah noksanlardan münezzehtir", "“Sübhanallah demek mizanın yarısını doldurur. Elhamdülillah demek ise teraziyi doldurmuş olur. Allah\'tan başka gerçek ilah yoktur, sadece O vardır, diyen kimse ile Allah arasında hiçbir perde yoktur. Cennette kendisiyle beraber oluncaya kadar…” \n(Tirmizî, Daavat, 87)"))
        sliderItemList.add(ViewpagerItem("سبحان الله","SÜBHANALLAH3","Allah noksanlardan münezzehtir", "“Sübhanallah demek mizanın yarısını doldurur. Elhamdülillah demek ise teraziyi doldurmuş olur. Allah\'tan başka gerçek ilah yoktur, sadece O vardır, diyen kimse ile Allah arasında hiçbir perde yoktur. Cennette kendisiyle beraber oluncaya kadar…” \n(Tirmizî, Daavat, 87)"))
        sliderItemList.add(ViewpagerItem("سبحان الله","ELHAMDULİLLAH","Allah noksanlardan münezzehtir", "“Sübhanallah demek mizanın yarısını doldurur. Elhamdülillah demek ise teraziyi doldurmuş olur. Allah\'tan başka gerçek ilah yoktur, sadece O vardır, diyen kimse ile Allah arasında hiçbir perde yoktur. Cennette kendisiyle beraber oluncaya kadar…” \n(Tirmizî, Daavat, 87)"))
        sliderItemList.add(ViewpagerItem("سبحان الله","ALLAHUEKBER","Allah noksanlardan münezzehtir", "“Sübhanallah demek mizanın yarısını doldurur. Elhamdülillah demek ise teraziyi doldurmuş olur. Allah\'tan başka gerçek ilah yoktur, sadece O vardır, diyen kimse ile Allah arasında hiçbir perde yoktur. Cennette kendisiyle beraber oluncaya kadar…” \n(Tirmizî, Daavat, 87)"))
        sliderItemList.add(ViewpagerItem("سبحان الله","ALLAH HÜMME SALLİ ALA SEYYİDİNA MUHAMMEDİN VE ALA ALİ SEYYİDİNA MUHAMMED","Allah noksanlardan münezzehtir", "“Sübhanallah demek mizanın yarısını doldurur. Elhamdülillah demek ise teraziyi doldurmuş olur. Allah\'tan başka gerçek ilah yoktur, sadece O vardır, diyen kimse ile Allah arasında hiçbir perde yoktur. Cennette kendisiyle beraber oluncaya kadar…” \n(Tirmizî, Daavat, 87)"))
        sliderItemList.add(ViewpagerItem("سبحان الله","LA HAVLE VE LA KUVVETE İLLA BİLLAH","Allah noksanlardan münezzehtir", "“Sübhanallah demek mizanın yarısını doldurur. Elhamdülillah demek ise teraziyi doldurmuş olur. Allah\'tan başka gerçek ilah yoktur, sadece O vardır, diyen kimse ile Allah arasında hiçbir perde yoktur. Cennette kendisiyle beraber oluncaya kadar…” \n(Tirmizî, Daavat, 87)"))

    }




}