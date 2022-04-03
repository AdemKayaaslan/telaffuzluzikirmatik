package com.ademkayaaslan.telaffuzluzikirmatik.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.initialization.InitializationStatus

import androidx.annotation.NonNull
import com.ademkayaaslan.telaffuzluzikirmatik.utils.Utils
import com.ademkayaaslan.telaffuzluzikirmatik.utils.Utils.Companion.isNetworkConnected

import com.google.android.gms.ads.initialization.OnInitializationCompleteListener

import com.google.android.gms.ads.MobileAds





class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val handler = Handler()
        val intent = Intent(this@SplashActivity, MainActivity::class.java)
        val timeDelay: Long

        timeDelay = if (isNetworkConnected(this@SplashActivity)) {
            MobileAds.initialize(
                this
            ) { Utils.loadAds(this@SplashActivity) }
            1500
        } else {
            800
        }

        handler.postDelayed(Runnable {
            startActivity(intent)
            finish()
        }, timeDelay)


    }

    override fun onBackPressed() {
        //Do nothing
    }
}
