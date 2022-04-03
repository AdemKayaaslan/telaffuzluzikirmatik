package com.ademkayaaslan.telaffuzluzikirmatik.view

import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.media.MediaPlayer
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.ademkayaaslan.telaffuzluzikirmatik.R
import com.ademkayaaslan.telaffuzluzikirmatik.adapter.ViewpagerAdapter
import com.ademkayaaslan.telaffuzluzikirmatik.model.ViewpagerItem
import com.ademkayaaslan.telaffuzluzikirmatik.viewmodel.DhikrViewModel
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import kotlinx.android.synthetic.main.dhikr_fragment.*
import java.util.*
import kotlin.collections.ArrayList

class DhikrFragment : Fragment() {

    companion object {
        fun newInstance() = DhikrFragment()
    }



    var isSoundOn = true
     var mediaPlayer: MediaPlayer? = null
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
    var totala0: Int = 0
    var totalb1: Int = 0
    var totalc2: Int = 0
    var totald3: Int = 0
    var totale4: Int = 0
    var totalf5: Int = 0
    var totalg6: Int = 0
    var totalh7: Int = 0
    var totali8: Int = 0
    var totalj9: Int = 0
    var totalk10: Int = 0
    var montha0: Int = 0
    var monthb1: Int = 0
    var monthc2: Int = 0
    var monthd3: Int = 0
    var monthe4: Int = 0
    var monthf5: Int = 0
    var monthg6: Int = 0
    var monthh7: Int = 0
    var monthi8: Int = 0
    var monthj9: Int = 0
    var monthk10: Int = 0
    var entrya0: Int = 0
    var entryb1: Int = 0
    var entryc2: Int = 0
    var entryd3: Int = 0
    var entrye4: Int = 0
    var entryf5: Int = 0
    var entryg6: Int = 0
    var entryh7: Int = 0
    var entryi8: Int = 0
    var entryj9: Int = 0
    var entryk10: Int = 0

    private lateinit var sliderItemList: ArrayList<ViewpagerItem>
    private lateinit var viewPagerAdapter: ViewpagerAdapter
    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var viewModel: DhikrViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)

        return inflater.inflate(R.layout.dhikr_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DhikrViewModel::class.java)
        activity?.getSharedPreferences(
            "com.ademkayaaslan.telaffuzluzikirmatik",
            Context.MODE_PRIVATE
        )?.also { sharedPreferences = it }
        activity?.actionBar?. setDisplayHomeAsUpEnabled(true)
        activity?.actionBar?. setDisplayShowHomeEnabled(true)
        virdButton()
        soundButton()
        loopButton()
        viewModel.getAllDhikrs()


       // adView.loadAd(adRequest)

        MobileAds.initialize(requireContext())
        val adRequest: AdRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)

        observeLiveData()

        positionInt = sharedPreferences.getInt("positionInt", 0)

    }


    fun virdButton() {

        virdButton.setOnClickListener {


            if (mediaPlayer?.isPlaying == false) {

                virdCounter()

                if (isSoundOn == true) {
                    mediaPlayer?.start()
                }

            }

            if (mediaPlayer?.isLooping == true) {
                mediaPlayer?.release()
                virdPositionMediaPlayerCreater()
            }
        }

        button_back.setOnClickListener {

            if (viewpager_dhikr.currentItem > 0) {
                viewpager_dhikr.currentItem -= 1
            } else {
                viewpager_dhikr.currentItem = 10
            }

        }


        button_foward.setOnClickListener {
            if (viewpager_dhikr.currentItem < 10) {
                viewpager_dhikr.currentItem += 1
            } else {
                viewpager_dhikr.currentItem = 0
            }
        }

    }


    fun soundButton() {

        button_sound.setOnClickListener {

            if (isSoundOn) {
                button_sound.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.ic_baseline_volume_off_24))
                isSoundOn = false
                mediaPlayer?.release()
                virdPositionMediaPlayerCreater()
            } else {
                button_sound.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.ic_baseline_volume_up_24))
                isSoundOn = true
                virdPositionMediaPlayerCreater()
            }

        }

    }


    fun loopButton() {

        button_loop.setOnClickListener {

            if (isSoundOn == true) {

                if (mediaPlayer?.isPlaying == true) {
                    mediaPlayer?.release()
                    virdPositionMediaPlayerCreater()
                } else {
                    var count = 0
                    mediaPlayer?.setOnCompletionListener(object : MediaPlayer.OnCompletionListener {
                        var maxCount = 1000
                        override fun onCompletion(mediaPlayer: MediaPlayer) {
                            if (count < maxCount) {
                                count++
                                mediaPlayer.seekTo(0)
                                mediaPlayer.start()

                                virdCounter()

                            }
                        }
                    })
                    mediaPlayer?.start()
                }

            } else {
                Toast.makeText(requireContext(), getString(R.string.sound_off), Toast.LENGTH_SHORT).show()
            }

        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu,menu)

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.statistics -> {
                Toast.makeText(requireContext(), "Yardım Masası", Toast.LENGTH_SHORT).show()
                false
            }
            R.id.reset -> {
                resetDhikr()
                true
            }
            R.id.home -> {
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun resetDhikr() {
        val alert = AlertDialog.Builder(requireContext())
        alert.setTitle(getString(R.string.reset_header))
        alert.setMessage(getString(R.string.reset_question))

        alert.setPositiveButton(getString(R.string.yes)) { dialog, which ->

            viewModel.deleteDhikrById(positionInt)
            virdReseter()

            Toast.makeText(requireContext(), getString(R.string.reset), Toast.LENGTH_LONG).show()
        }
        alert.setNegativeButton(getString(R.string.no)) { dialog, which ->
            Toast.makeText(requireContext(), getString(R.string.noreset), Toast.LENGTH_LONG).show()
        }
        alert.show()

    }

    fun virdCounter() {
        if (positionInt == 0) {
            a0 += 1
            virdWriter()
        } else if (positionInt == 1) {
            b1 += 1
            virdWriter()
        } else if (positionInt == 2) {
            c2 += 1
            virdWriter()
        } else if (positionInt == 3) {
            d3 += 1
            virdWriter()
        } else if (positionInt == 4) {
            e4 += 1
            virdWriter()
        } else if (positionInt == 5) {
            f5 = f5.plus(1)
            virdWriter()
        } else if (positionInt == 6) {
            g6 = g6.plus(1)
            virdWriter()
        } else if (positionInt == 7) {
            h7 = h7.plus(1)
            virdWriter()
        } else if (positionInt == 8) {
            i8 = i8.plus(1)
            virdWriter()
        } else if (positionInt == 9) {
            j9 = j9.plus(1)
            virdWriter()
        } else if (positionInt == 10) {
            k10 = k10.plus(1)
            virdWriter()
        }


    }


    fun virdWriter() {
        if (positionInt == 0) {
            textView.text = "vird : " + a0

            textView_total.text = getString(R.string.total_dhikr)+(totala0 + a0)
            textView_month.text = getString(R.string.month_dhikr)+ (montha0 + a0)
        } else if (positionInt == 1) {
            textView.text = "vird : " + b1
            textView_total.text = getString(R.string.total_dhikr)+(totalb1 + b1)
            textView_month.text = getString(R.string.month_dhikr)+ (monthb1 + b1)
        } else if (positionInt == 2) {
            textView.text = "vird : " + c2
            textView_total.text = getString(R.string.total_dhikr)+(totalc2 + c2)
            textView_month.text = getString(R.string.month_dhikr)+ (monthc2 + c2)
        } else if (positionInt == 3) {
            textView.text = "vird : " + d3
            textView_total.text = getString(R.string.total_dhikr)+(totald3 + d3)
            textView_month.text = getString(R.string.month_dhikr)+ (monthd3 + d3)
        } else if (positionInt == 4) {
            textView.text = "vird : " + e4
            textView_total.text = getString(R.string.total_dhikr)+(totale4 + e4)
            textView_month.text = getString(R.string.month_dhikr)+ (monthe4 + e4)
        } else if (positionInt == 5) {
            textView.text = "vird : " + f5
            textView_total.text = getString(R.string.total_dhikr)+(totalf5 + f5)
            textView_month.text = getString(R.string.month_dhikr)+ (monthf5 + f5)
        } else if (positionInt == 6) {
            textView.text = "vird : " + g6
            textView_total.text = getString(R.string.total_dhikr)+(totalg6 + g6)
            textView_month.text = getString(R.string.month_dhikr)+ (monthg6 + g6)
        } else if (positionInt == 7) {
            textView.text = "vird : " + h7
            textView_total.text = getString(R.string.total_dhikr)+(totalh7 + h7)
            textView_month.text = getString(R.string.month_dhikr)+ (monthh7 + h7)
        } else if (positionInt == 8) {
            textView.text = "vird : " + i8
            textView_total.text = getString(R.string.total_dhikr)+(totali8 + i8)
            textView_month.text = getString(R.string.month_dhikr)+ (monthi8 + i8)
        } else if (positionInt == 9) {
            textView.text = "vird : " + j9
            textView_total.text = getString(R.string.total_dhikr)+(totalj9 + j9)
            textView_month.text = getString(R.string.month_dhikr)+ (monthj9 + j9)
        } else if (positionInt == 10) {
            textView.text = "vird : " + k10
            textView_total.text = getString(R.string.total_dhikr)+(totalk10 + k10)
            textView_month.text = getString(R.string.month_dhikr)+ (monthk10 + k10)
        }


    }

    fun virdReseter() {
        if (positionInt == 0) {
            a0=0
            textView.text = "vird :" + a0
        } else if (positionInt == 1) {
            b1 = 0
            textView.text = "vird :" + b1
        } else if (positionInt == 2) {
            c2 = 0
            textView.text = "vird :" + c2
        } else if (positionInt == 3) {
            d3=0
            textView.text = "vird :" + d3
        } else if (positionInt == 4) {
            e4=0
            textView.text = "vird :" + e4
        } else if (positionInt == 5) {
            f5=0
            textView.text = "vird :" + f5
        } else if (positionInt == 6) {
            g6=0
            textView.text = "vird :" + g6
        } else if (positionInt == 7) {
            h7=0
            textView.text = "vird :" + h7
        } else if (positionInt == 8) {
            i8=0
            textView.text = "vird :" + i8
        } else if (positionInt == 9) {
            j9=0
            textView.text = "vird :" + j9
        } else if (positionInt == 10) {
            k10=0
            textView.text = "vird :" + k10
        }


    }

    override fun onPause() {
        super.onPause()

        val timestamp = Calendar.getInstance().timeInMillis

        if (a0 > entrya0) {
            viewModel.saveDhikr(a0-entrya0, 0, timestamp)
        }
        if (b1 > entryb1) {
            viewModel.saveDhikr(b1-entryb1, 1, timestamp)
        }
        if (c2 > entryc2) {
            viewModel.saveDhikr(c2-entryc2, 2, timestamp)
        }
        if (d3 > entryd3) {
            viewModel.saveDhikr(d3-entryd3, 3, timestamp)
        }
        if (e4 > entrye4) {
            viewModel.saveDhikr(e4-entrye4, 4, timestamp)
        }
        if (f5 > entryf5) {
            viewModel.saveDhikr(f5-entryf5, 5, timestamp)
        }
        if (g6 > entryg6) {
            viewModel.saveDhikr(g6-entryg6, 6, timestamp)
        }
        if (h7 > entryh7) {
            viewModel.saveDhikr(h7-entryh7, 7, timestamp)
        }
        if (i8 > entryi8) {
            viewModel.saveDhikr(i8-entryi8, 8, timestamp)
        }
        if (j9 > entryj9) {
            viewModel.saveDhikr(j9-entryj9, 9, timestamp)
        }
        if (k10 > entryk10) {
            viewModel.saveDhikr(k10-entryk10, 10, timestamp)
        }

        sharedPreferences.edit().putInt("positionInt", positionInt).apply()

        entrya0 = a0
        entryb1 = b1
        entryc2 = c2
        entryd3 = d3
        entrye4 = e4
        entryf5 = f5
        entryg6 = g6
        entryh7 = h7
        entryi8 = i8
        entryj9 = j9
        entryk10 = k10

    }


    fun observeLiveData() {

        viewModel.allDhikrs.observe(viewLifecycleOwner, androidx.lifecycle.Observer {

            it?.let {

                for (dhikr in it) {
                    when (dhikr.dhikrId) {

                        0 -> totala0 += dhikr.dhikrCount
                        1 -> totalb1 += dhikr.dhikrCount
                        2 -> totalc2 += dhikr.dhikrCount
                        3 -> totald3 += dhikr.dhikrCount
                        4 -> totale4 += dhikr.dhikrCount
                        5 -> totalf5 += dhikr.dhikrCount
                        6 -> totalg6 += dhikr.dhikrCount
                        7 -> totalh7 += dhikr.dhikrCount
                        8 -> totali8 += dhikr.dhikrCount
                        9 -> totalj9 += dhikr.dhikrCount
                        10 -> totalk10 += dhikr.dhikrCount


                    }
                }

                val cal = Calendar.getInstance()
                cal.set(Calendar.DAY_OF_MONTH,1)
                cal.set(Calendar.HOUR_OF_DAY,0)
                cal.set(Calendar.MINUTE,0)
                cal.set(Calendar.SECOND,0)
                viewModel.getMonthDhikrs(cal.timeInMillis,positionInt)


            }

        })


        viewModel.monthDhikrs.observe(viewLifecycleOwner, androidx.lifecycle.Observer {

            it?.let {

                for (dhikr in it) {
                    when (dhikr.dhikrId) {

                        0 -> montha0 += dhikr.dhikrCount
                        1 -> monthb1 += dhikr.dhikrCount
                        2 -> monthc2 += dhikr.dhikrCount
                        3 -> monthd3 += dhikr.dhikrCount
                        4 -> monthe4 += dhikr.dhikrCount
                        5 -> monthf5 += dhikr.dhikrCount
                        6 -> monthg6 += dhikr.dhikrCount
                        7 -> monthh7 += dhikr.dhikrCount
                        8 -> monthi8 += dhikr.dhikrCount
                        9 -> monthj9 += dhikr.dhikrCount
                        10 -> monthk10 += dhikr.dhikrCount


                    }
                }

                sliderItems()
                itemSliderView()
                viewpager_dhikr.currentItem = positionInt

            }

        })

    }

    fun virdPositionMediaPlayerCreater() {

        if (positionInt == 0) {
            mediaPlayer = MediaPlayer.create(requireContext(), R.raw.yaallah)
        } else if (positionInt == 1) {
            mediaPlayer = MediaPlayer.create(requireContext(), R.raw.lailaheillallah)
        } else if (positionInt == 2) {
            mediaPlayer = MediaPlayer.create(requireContext(), R.raw.subhanallah)
        } else if (positionInt == 3) {
            mediaPlayer = MediaPlayer.create(requireContext(), R.raw.elhamdulillah)
        } else if (positionInt == 4) {
            mediaPlayer = MediaPlayer.create(requireContext(), R.raw.allahuekber)
        } else if (positionInt == 5) {
            mediaPlayer = MediaPlayer.create(requireContext(), R.raw.salavat)
        } else if (positionInt == 6) {
            mediaPlayer = MediaPlayer.create(requireContext(), R.raw.subhanallahilazim)
        } else if (positionInt == 7) {
            mediaPlayer = MediaPlayer.create(requireContext(), R.raw.illabillah)
        } else if (positionInt == 8) {
            mediaPlayer = MediaPlayer.create(requireContext(), R.raw.hasbunallahu)
        } else if (positionInt == 9) {
            mediaPlayer = MediaPlayer.create(requireContext(), R.raw.minezzalimin)
        } else if (positionInt == 10) {
            mediaPlayer = MediaPlayer.create(requireContext(), R.raw.vallahuekber)
        }
    }


    private fun sliderItems() {
        sliderItemList = ArrayList()
        viewPagerAdapter = ViewpagerAdapter(viewpager_dhikr, sliderItemList)
        viewpager_dhikr.adapter = viewPagerAdapter
        viewpager_dhikr.clipToPadding = false
        viewpager_dhikr.clipChildren = false
        viewpager_dhikr.offscreenPageLimit = 1
        viewpager_dhikr.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER


        spring_dots_indicator.setViewPager2(viewpager_dhikr)

        viewpager_dhikr.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    positionInt = viewpager_dhikr.currentItem
                    virdWriter()

                    mediaPlayer?.release()
                    virdPositionMediaPlayerCreater()

                }
            }
        )
    }


    private fun itemSliderView() {

        val viewPagerYaAllah = ViewpagerItem(
            getString(R.string.arabic_ya_allah),
            getString(R.string.name_ya_allah),
            getString(R.string.explanation_ya_allah),
            getString(R.string.body_ya_allah)
        )
        val viewPagerLailaheillallah = ViewpagerItem(
            getString(R.string.arabic_lailaheillallah),
            getString(R.string.name_lailaheillallah),
            getString(R.string.explanation_lailaheillallah),
            getString(R.string.body_lailaheillallah)
        )
        val viewPagerSubhanallah = ViewpagerItem(
            getString(R.string.arabic_subhanallah),
            getString(R.string.name_subhanallah),
            getString(R.string.explanation_subhanallah),
            getString(R.string.body_subhanallah)
        )

        val viewPagerAlhamdulillah = ViewpagerItem(
            getString(R.string.arabic_alhamdullilah),
            getString(R.string.name_alhamdullilah),
            getString(R.string.explanation_alhamdullilah),
            getString(R.string.body_alhamdullilah)
        )
        val viewPagerAllahuekber = ViewpagerItem(
            getString(R.string.arabic_allahuekber),
            getString(R.string.name_allahuekber),
            getString(R.string.explanation_allahuekber),
            getString(R.string.body_allahuekber)
        )
        val viewPagerSalawat = ViewpagerItem(
            getString(R.string.arabic_salawat),
            getString(R.string.name_salawat),
            getString(R.string.explanation_salawat),
            getString(R.string.body_salawat)
        )

        val viewPagerSubhanallahiVebihamdihi = ViewpagerItem(
            getString(R.string.arabic_subhanallahi_vebihamdihi),
            getString(R.string.name_subhanallahi_vebihamdihi),
            getString(R.string.explanation_subhanallahi_vebihamdihi),
            getString(R.string.body_subhanallahi_vebihamdihi)
        )
        val viewPagerLahavle = ViewpagerItem(
            getString(R.string.arabic_lahavle),
            getString(R.string.name_lahavle),
            getString(R.string.explanation_lahavle),
            getString(R.string.body_lahavle)
        )
        val viewPagerHasbunallahu = ViewpagerItem(
            getString(R.string.arabic_hasbunallahu),
            getString(R.string.name_hasbunallahu),
            getString(R.string.explanation_hasbunallahu),
            getString(R.string.body_hasbunallahu)
        )

        val viewPagerLailahe = ViewpagerItem(
            getString(R.string.arabic_lailahe),
            getString(R.string.name_lailahe),
            getString(R.string.explanation_lailahe),
            getString(R.string.body_lailahe)
        )
        val viewPagerSubhanallahiVelhamdulillahi = ViewpagerItem(
            getString(R.string.arabic_subhanallahi_velhamdulillahi),
            getString(R.string.name_subhanallahi_velhamdulillahi),
            getString(R.string.explanation_subhanallahi_velhamdulillahi),
            getString(R.string.body_subhanallahi_velhamdulillahi)
        )


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