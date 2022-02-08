package com.ademkayaaslan.telaffuzluzikirmatik.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.BitmapFactory
import android.os.Build
import android.provider.Settings.Global.getString
import android.util.Log
import androidx.core.app.NotificationCompat
import com.ademkayaaslan.telaffuzluzikirmatik.R
import com.ademkayaaslan.telaffuzluzikirmatik.view.SplashActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class MyFirebaseMessagingService: FirebaseMessagingService() {
    private var builder: NotificationCompat.Builder? = null
    private var pIntent: PendingIntent? = null
    private var mContext: Context? = null

    override fun onNewToken(token: String) {
        Log.d("Firebase", "Refreshed token: $token")
    }

    fun onMessageReceived(context: Context, remoteMessage: RemoteMessage) {
        mContext = context
        if (remoteMessage.notification != null) {
            //Consoledan mesaj gönderildiğinde burası tetiklenecektir
            val title = remoteMessage.notification!!.title
            sendNotification(title)
        }
    }

    private fun sendNotification(messageTitle: String?) {
        val intent = Intent(mContext, SplashActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        pIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PendingIntent.getActivity(
                mContext,
                0,
                intent,
                PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
            )
        } else {
            PendingIntent.getActivity(mContext, 0, intent, PendingIntent.FLAG_ONE_SHOT)
        }
        val notificationManager =
            mContext?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelName = "Other notifications"
            val channelDesc =
                "Weekly and monthly new reviews, important astrological events, app updates..."
            val mChannel =
                NotificationChannel(0.toString(), channelName, NotificationManager.IMPORTANCE_HIGH)
            mChannel.description = channelDesc
            mChannel.enableVibration(true)
            mChannel.vibrationPattern = longArrayOf(500, 500, 500, 500)
            notificationManager.createNotificationChannel(mChannel)
            builder = NotificationCompat.Builder(mContext!!, 0.toString())
        } else {
            builder = NotificationCompat.Builder(mContext!!)
        }
        notificationBuild(mContext!!.getString(R.string.app_name), messageTitle)

        //Send notification
        val notification: Notification = builder?.build()!!
        notificationManager.notify(0, notification)
    }

    private fun notificationBuild(messageTitle: String, messageBody: String?) {
        val bm = BitmapFactory.decodeResource(mContext!!.resources, R.mipmap.ic_launcher)
        builder!!.setContentTitle(messageTitle)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setLargeIcon(bm)
            .setContentTitle(messageTitle)
            .setContentText(messageBody)
            .setAutoCancel(true)
            .setVibrate(longArrayOf(500, 500, 500, 500))
            .setContentIntent(pIntent)
    }
}