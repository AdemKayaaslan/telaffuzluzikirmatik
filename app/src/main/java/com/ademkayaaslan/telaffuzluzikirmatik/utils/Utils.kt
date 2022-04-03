package com.ademkayaaslan.telaffuzluzikirmatik.utils

import android.annotation.SuppressLint
import android.app.AlarmManager

import android.app.PendingIntent

import android.os.Build

import android.content.Intent

import android.content.Context.ALARM_SERVICE
import android.widget.Toast


import com.ademkayaaslan.telaffuzluzikirmatik.view.MainActivity

import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;

import android.app.Activity
import android.content.Context


import android.net.ConnectivityManager
import com.ademkayaaslan.telaffuzluzikirmatik.R
import com.ademkayaaslan.telaffuzluzikirmatik.receiver.AlarmReceiver
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import java.util.*


class Utils {

    companion object {
        var admobInterstitial: InterstitialAd? = null
        var adCount = 0

        @SuppressLint("MissingPermission")
        fun isNetworkConnected(mContext: Context): Boolean {
            val cm = mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return cm.activeNetworkInfo != null
        }

        fun loadAds(mContext: Context) {

            if (adCount < 2) {
                val adRequest: AdRequest = AdRequest.Builder().build()
                InterstitialAd.load(mContext,
                    mContext.getString(R.string.interstitial_admob),
                    adRequest,
                    object : InterstitialAdLoadCallback() {
                        override fun onAdLoaded(interstitialAd: InterstitialAd) {
                            admobInterstitial = interstitialAd
                        }

                        override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                            admobInterstitial = null
                        }
                    })
            }

        }

        fun showAds(activity: Activity, intent: Intent?) {

            if (admobInterstitial != null) {
                admobInterstitial!!.setFullScreenContentCallback(object :
                    FullScreenContentCallback() {
                    override fun onAdDismissedFullScreenContent() {
                        // Called when fullscreen content is dismissed.
                        if (activity != null){
                            activity.startActivity(intent)
                        }

                        admobInterstitial = null
                        loadAds(activity)
                    }

                    override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                        // Called when fullscreen content failed to show.
                    }

                    override fun onAdShowedFullScreenContent() {
                        // Called when fullscreen content is shown.
                        // Make sure to set your reference to null so you don't
                        // show it a second time.
                        admobInterstitial = null
                    }
                })
                admobInterstitial!!.show(activity)
                adCount++
            } else {
                // Ads doesn't loaded.
                    if (activity != null){
                        activity.startActivity(intent)
                    }
            }
            if (adCount === 2) {
                adCount++
                Toast.makeText(
                    activity,
                    activity.getString(R.string.last_ads_info),
                    Toast.LENGTH_SHORT
                )
                    .show()
            }

        }

        fun AlarmBuilder(mContext: Context) {
            val alarmManager = mContext.getSystemService(ALARM_SERVICE) as AlarmManager
            val myIntent = Intent(mContext, AlarmReceiver::class.java)
            val pendingIntent: PendingIntent
            pendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                PendingIntent.getBroadcast(
                    mContext,
                    100,
                    myIntent.putExtra("RequestCode", 100),
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )
            } else {
                PendingIntent.getBroadcast(
                    mContext, 100,
                    myIntent.putExtra("RequestCode", 100), PendingIntent.FLAG_UPDATE_CURRENT
                )
            }
            val pendingIntent2: PendingIntent
            pendingIntent2 = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                PendingIntent.getBroadcast(
                    mContext,
                    200,
                    myIntent.putExtra("RequestCode", 200),
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )
            } else {
                PendingIntent.getBroadcast(
                    mContext, 200,
                    myIntent.putExtra("RequestCode", 200), PendingIntent.FLAG_UPDATE_CURRENT
                )
            }
            createWeeklyAlarm(alarmManager, pendingIntent2)
        }


        fun createWeeklyAlarm(alarmManager: AlarmManager, pendingIntent: PendingIntent?) {

            val currentTime: Calendar = Calendar.getInstance()
            //  val alarmHour: Int = prefs.getInt("alarmHour", 10)
            //  val alarmMinute: Int = prefs.getInt("alarmMinute", 0)
            val alarmHour: Int = 10
            val alarmMinute: Int = 0
            val calendar2: Calendar = Calendar.getInstance()
            calendar2.set(Calendar.HOUR_OF_DAY, alarmHour)
            calendar2.set(Calendar.MINUTE, alarmMinute)
            calendar2.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY)
            if (currentTime.getTimeInMillis() < calendar2.getTimeInMillis()) {
                alarmManager.setInexactRepeating(
                    AlarmManager.RTC, calendar2.getTimeInMillis(),
                    AlarmManager.INTERVAL_DAY * 7, pendingIntent
                )
            } else {
                alarmManager.setInexactRepeating(
                    AlarmManager.RTC, 1000 * 60 * 60 * 24 * 7 + calendar2.getTimeInMillis(),
                    AlarmManager.INTERVAL_DAY * 7, pendingIntent
                )
            }

        }
    }
}