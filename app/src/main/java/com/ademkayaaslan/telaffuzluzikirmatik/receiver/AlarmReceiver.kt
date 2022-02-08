package com.ademkayaaslan.telaffuzluzikirmatik.receiver

import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import com.ademkayaaslan.telaffuzluzikirmatik.R
import com.ademkayaaslan.telaffuzluzikirmatik.utils.Utils.Companion.createWeeklyAlarm
import com.ademkayaaslan.telaffuzluzikirmatik.view.SplashActivity
import java.text.SimpleDateFormat
import java.util.*


class AlarmReceiver:BroadcastReceiver() {
    private var builder: NotificationCompat.Builder? = null
    private var pIntent: PendingIntent? = null

    override fun onReceive(context: Context, intent: Intent) {
        if ("android.intent.action.BOOT_COMPLETED" == intent.action) {
            //If phone restarted, this section detects that and recreate alarm
            scheduleAlarms(context)
        } else {
            //This is daily alarm section.
            val requestCode = intent.extras!!.getInt("RequestCode")
            generateNotification(context, requestCode)
        }
    }

    private fun scheduleAlarms(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val myIntent = Intent(context, AlarmReceiver::class.java)
        val pendingIntent2: PendingIntent
        pendingIntent2 = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PendingIntent.getBroadcast(
                context,
                200,
                myIntent.putExtra("RequestCode", 200),
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        } else {
            PendingIntent.getBroadcast(
                context, 200,
                myIntent.putExtra("RequestCode", 200), PendingIntent.FLAG_UPDATE_CURRENT
            )
        }
        createWeeklyAlarm(alarmManager, pendingIntent2)
    }

    private fun generateNotification(context: Context, requestCode: Int) {
        val intentLauncher = Intent(context, SplashActivity::class.java)
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        pIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PendingIntent.getActivity(
                context,
                0,
                intentLauncher,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        } else {
            PendingIntent.getActivity(context, 0, intentLauncher, PendingIntent.FLAG_UPDATE_CURRENT)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelName = "Daily notification"
            val channelDesc = "It notifies you about new reviews on a daily basis."
            val mChannel =
                NotificationChannel(0.toString(), channelName, NotificationManager.IMPORTANCE_HIGH)
            mChannel.description = channelDesc
            mChannel.enableVibration(true)
            mChannel.vibrationPattern = longArrayOf(500, 500, 500, 500)
            notificationManager.createNotificationChannel(mChannel)
            builder = NotificationCompat.Builder(context, 0.toString())
        } else {
            builder = NotificationCompat.Builder(context)
        }
        notificationBuild(context, requestCode)

        //Send notification
        val notification: Notification = builder!!.build()
        notificationManager.notify(0, notification)
    }

    private fun notificationBuild(context: Context, requestCode: Int) {
        val bm = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher)
        val sdf = SimpleDateFormat("d MMMM yyyy EEEE", Locale.getDefault())
        val sdfWeekly = SimpleDateFormat("d MMMM", Locale.getDefault())
        val notificationContext: String
        notificationContext = sdfWeekly.format(Date()).toString() + "-" + sdfWeekly.format(
            getDateWithOffset(
                7,
                Date()
            )
        ) + ": " + context.getString(R.string.weeklyNotificationSuffix)
        builder!!.setContentTitle(context.getString(R.string.app_name))
            .setContentText(notificationContext)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setAutoCancel(true)
            .setContentIntent(pIntent)
            .setLargeIcon(bm)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setVibrate(longArrayOf(500, 500, 500, 500))
    }

    fun getDateWithOffset(offset: Int, date: Date?): Date? {
        val calendar: Calendar = Calendar.getInstance()
        calendar.setTime(date)
        calendar.add(Calendar.DAY_OF_MONTH, offset)
        return calendar.getTime()
    }

}