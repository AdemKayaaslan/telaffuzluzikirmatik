package com.ademkayaaslan.telaffuzluzikirmatik

import android.content.Context
import android.content.SharedPreferences
import android.media.MediaPlayer
import android.media.MediaPlayer.OnCompletionListener
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var adInt:Int = 5
    private lateinit var mInterstitialAd: InterstitialAd
    lateinit var mAdView : AdView
    lateinit var mediaPlayer:MediaPlayer
    var isWorkedByOnCreate = true
    var isSoundOn = true
    var a0: Int? = null
    var b1: Int? = null
    var c2: Int? = null
    var d3: Int? = null
    var e4: Int? = null
    var f5: Int? = null
    var g6: Int? = null
    var h7: Int? = null
    var ı8: Int? = null
    var i9: Int? = null
    var j10: Int? = null
    var positionInt:Int?=null
    lateinit var sharedPreferences : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        sharedPreferences = this.getSharedPreferences("com.ademkayaaslan.telaffuzluzikirmatik", Context.MODE_PRIVATE)

        a0 = sharedPreferences.getInt("a0",0)
        b1 = sharedPreferences.getInt("b1",0)
        c2 = sharedPreferences.getInt("c2",0)
        d3 = sharedPreferences.getInt("d3",0)
        e4 = sharedPreferences.getInt("e4",0)
        f5 = sharedPreferences.getInt("f5",0)
        g6 = sharedPreferences.getInt("g6",0)
        h7 = sharedPreferences.getInt("h7",0)
        ı8 = sharedPreferences.getInt("ı8",0)
        i9 = sharedPreferences.getInt("i9",0)
        j10 = sharedPreferences.getInt("j10",0)
        positionInt= sharedPreferences.getInt("positionInt",0)

        //   ----------------  reklam  ---------------
        MobileAds.initialize(this) {}
        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        mInterstitialAd = InterstitialAd(this)
        mInterstitialAd.adUnitId = "ca-app-pub-3665001246595939/6617121575"
        mInterstitialAd.loadAd(AdRequest.Builder().build())

        mInterstitialAd.adListener = object : AdListener() {
            override fun onAdOpened() {
                super.onAdOpened()
                mediaPlayer.release()
            }

            override fun onAdClosed() {
                super.onAdClosed()
                virdPosition()
            }
        }
        //   ----------------  reklam  ---------------
        
        //    --------------------  spinner  -----------------------
        val items = arrayOf("1) Ya Allah","2) La ilahe illallah", "3) Subhanallah","4) Elhamdulillah","5) Allahuekber",
            "6) Allah hümme salli ala seyyidina muhammedin ve ala ali seyyidina muhammed","7) Subhanallahi vebihamdihi subhanallahil azim",
            "8) La havle vela kuvvete illa billah","9) Hasbünallahu ve ni'mel vekil",
            "10) La ilahe illa ente subhaneke inni kuntü minez zalimin","11) Subhanallahi velhamdulillahi vela ilahe illallahu vallahu ekber")

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        spinner.adapter=adapter


        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                    virdPosition()
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (isWorkedByOnCreate == false) {
                    if (!mInterstitialAd.isLoaded) {
                        mInterstitialAd.loadAd(AdRequest.Builder().build())
                    }
                    positionInt = position
                    mediaPlayer.release()
                    virdPosition()
                    interstitial()
                } else {
                    isWorkedByOnCreate = false
                }


            }

        }
        //    --------------------  spinner  -----------------------

    }

    override fun onResume() {
        super.onResume()
        virdPosition()
        spinner.setSelection(positionInt!!)

    }



    fun virdCounter () {
            if (positionInt == 0) {
                a0 = a0?.plus(1)
                textView.text = "vird :" + a0
            } else if (positionInt == 1) {
                b1 = b1?.plus(1)
                textView.text = "vird :" + b1
            } else if (positionInt == 2) {
                c2 = c2?.plus(1)
                textView.text = "vird :" + c2
            } else if (positionInt == 3) {
                d3 = d3?.plus(1)
                textView.text = "vird :" + d3
            } else if (positionInt == 4) {
                e4 = e4?.plus(1)
                textView.text = "vird :" + e4
            } else if (positionInt == 5) {
                f5 = f5?.plus(1)
                textView.text = "vird :" + f5
            } else if (positionInt == 6) {
                g6 = g6?.plus(1)
                textView.text = "vird :" + g6
            } else if (positionInt == 7) {
                h7 = h7?.plus(1)
                textView.text = "vird :" + h7
            } else if (positionInt == 8) {
                ı8 = ı8?.plus(1)
                textView.text = "vird :" + ı8
            } else if (positionInt == 9) {
                i9 = i9?.plus(1)
                textView.text = "vird :" + i9
            } else if (positionInt == 10) {
                j10 = j10?.plus(1)
                textView.text = "vird :" + j10
            }


    }


    fun virdButton (view: View) {

        view.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    if (!mediaPlayer.isPlaying) {
                        imageButton.setImageResource(R.drawable.buttondown)
                    }

                }
                MotionEvent.ACTION_UP -> {
                    imageButton.setImageResource(R.drawable.buttonup)
                }
            }
            false
        }

        if (!mediaPlayer.isPlaying) {

            virdCounter()

            if (isSoundOn == true) {
                mediaPlayer?.start()
            }

        }


    }


    fun virdPosition () {

        if (positionInt == 0) {
            textView.text = "vird :" + a0
            imageView.setImageResource(R.drawable.yaallah)
            mediaPlayer = MediaPlayer.create(this, R.raw.yaallah)
        } else if (positionInt == 1) {
            textView.text = "vird :" + b1
            imageView.setImageResource(R.drawable.lailahe)
            mediaPlayer = MediaPlayer.create(this, R.raw.lailaheillallah)
        } else if (positionInt == 2) {
            textView.text = "vird :" + c2
            imageView.setImageResource(R.drawable.subhan)
            mediaPlayer = MediaPlayer.create(this, R.raw.subhanallah)
        } else if (positionInt == 3) {
            textView.text = "vird :" + d3
            imageView.setImageResource(R.drawable.elhamd)
            mediaPlayer = MediaPlayer.create(this, R.raw.elhamdulillah)
        } else if (positionInt == 4) {
            textView.text = "vird :" + e4
            imageView.setImageResource(R.drawable.allahuekber)
            mediaPlayer = MediaPlayer.create(this, R.raw.allahuekber)
        } else if (positionInt == 5) {
            textView.text = "vird :" + f5
            imageView.setImageResource(R.drawable.salavat)
            mediaPlayer = MediaPlayer.create(this, R.raw.salavat)
        } else if (positionInt == 6) {
            textView.text = "vird :" + g6
            imageView.setImageResource(R.drawable.subhanallahilazim)
            mediaPlayer = MediaPlayer.create(this, R.raw.subhanallahilazim)
        } else if (positionInt == 7) {
            textView.text = "vird :" + h7
            imageView.setImageResource(R.drawable.illabillah)
            mediaPlayer = MediaPlayer.create(this, R.raw.illabillah)
        } else if (positionInt == 8) {
            textView.text = "vird :" + ı8
            imageView.setImageResource(R.drawable.hasbunallahu)
            mediaPlayer = MediaPlayer.create(this, R.raw.hasbunallahu)
        } else if (positionInt == 9) {
            textView.text = "vird :" + i9
            imageView.setImageResource(R.drawable.minezzalimin)
            mediaPlayer = MediaPlayer.create(this, R.raw.minezzalimin)
        } else if (positionInt == 10) {
            textView.text = "vird :" + j10
            imageView.setImageResource(R.drawable.vallahuekber)
            mediaPlayer = MediaPlayer.create(this, R.raw.vallahuekber)
        } else{}

}

    fun interstitial () {
        adInt++
        if (mInterstitialAd.isLoaded && adInt%5==0) {
            mInterstitialAd.show()
        }
    }

    fun back (view: View) {
        if (positionInt!! > 0) {
            mediaPlayer.release()
            positionInt = positionInt?.minus(1)
            spinner.setSelection(positionInt!!)
        } else {
            mediaPlayer.release()
            positionInt = 10
            spinner.setSelection(positionInt!!)
        }

    }

    fun next(view: View) {
        if (positionInt!! < 10) {
            mediaPlayer.release()
            positionInt=positionInt?.plus(1)
            spinner.setSelection(positionInt!!)
        } else {
            mediaPlayer.release()
            positionInt=0
            spinner.setSelection(positionInt!!)
        }

    }

    fun restart (view: View) {

        val alert = AlertDialog.Builder(this@MainActivity)
        alert.setTitle("Sıfırlama")
        alert.setMessage("Vird sayısını sıfırlamak istediğinizden emin misiniz?")

        alert.setPositiveButton("Evet") {dialog, which ->

            if (mInterstitialAd.isLoaded) {
                mInterstitialAd.show()
            }

            if (positionInt == 0) {
                sharedPreferences.edit().remove("a0").apply()
                a0=0
                textView.text="vird :"+a0
            } else if (positionInt == 1) {
                sharedPreferences.edit().remove("b1").apply()
                b1=0
                textView.text="vird :"+b1
            } else if (positionInt == 2) {
                sharedPreferences.edit().remove("c2").apply()
                c2=0
                textView.text="vird :"+c2
            } else if (positionInt == 3) {
                sharedPreferences.edit().remove("d3").apply()
                d3=0
                textView.text="vird :"+d3
            } else if (positionInt == 4) {
                sharedPreferences.edit().remove("e4").apply()
                e4=0
                textView.text="vird :"+e4
            } else if (positionInt == 5) {
                sharedPreferences.edit().remove("f5").apply()
                f5=0
                textView.text="vird :"+f5
            } else if (positionInt == 6) {
                sharedPreferences.edit().remove("g6").apply()
                g6=0
                textView.text="vird :"+g6
            } else if (positionInt == 7) {
                sharedPreferences.edit().remove("h7").apply()
                h7=0
                textView.text="vird :"+h7
            } else if (positionInt == 8) {
                sharedPreferences.edit().remove("ı8").apply()
                ı8=0
                textView.text="vird :"+ı8
            } else if (positionInt == 9) {
                sharedPreferences.edit().remove("i9").apply()
                i9=0
                textView.text="vird :"+i9
            } else if (positionInt == 10) {
                sharedPreferences.edit().remove("j10").apply()
                j10=0
                textView.text="vird :"+j10
            }


            Toast.makeText(this,"Sıfırlandı",Toast.LENGTH_LONG).show()
        }
        alert.setNegativeButton("Hayır") {dialog, which ->
            Toast.makeText(this,"Sıfırlanmadı",Toast.LENGTH_LONG).show()
        }
        alert.show()

    }

    fun sound (view: View) {
        if(isSoundOn==true) {
            isSoundOn = false
            mediaPlayer.release()
            sound.setImageResource(R.drawable.sff)
            virdPosition()
        } else if (isSoundOn == false) {
            isSoundOn = true
            sound.setImageResource(R.drawable.sound)
            virdPosition()
        }
    }

    fun loop (view: View) {

        if (!mInterstitialAd.isLoaded) {
            mInterstitialAd.loadAd(AdRequest.Builder().build())
        }

        if (isSoundOn==true) {

            if (mediaPlayer.isPlaying) {
                mediaPlayer.release()
                virdPosition()
            } else {
                var count = 0
                mediaPlayer.setOnCompletionListener(object : OnCompletionListener {
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
                mediaPlayer.start()
            }

        } else {
            Toast.makeText(this,"Ses kapalı!",Toast.LENGTH_SHORT).show()
        }

    }

    override fun onStop() {
        super.onStop()

        sharedPreferences.edit().putInt("a0",a0!!).apply()
        sharedPreferences.edit().putInt("b1",b1!!).apply()
        sharedPreferences.edit().putInt("c2",c2!!).apply()
        sharedPreferences.edit().putInt("d3",d3!!).apply()
        sharedPreferences.edit().putInt("e4",e4!!).apply()
        sharedPreferences.edit().putInt("f5",f5!!).apply()
        sharedPreferences.edit().putInt("g6",g6!!).apply()
        sharedPreferences.edit().putInt("h7",h7!!).apply()
        sharedPreferences.edit().putInt("ı8",ı8!!).apply()
        sharedPreferences.edit().putInt("i9",i9!!).apply()
        sharedPreferences.edit().putInt("j10",j10!!).apply()
        sharedPreferences.edit().putInt("positionInt",positionInt!!).apply()

        mediaPlayer.release()
    }


}