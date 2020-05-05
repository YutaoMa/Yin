package me.ytma.yin

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.IBinder

class HeadsetStateService : Service() {
    private var headsetStateBroadcastReceiver: HeadsetStateBroadcastReceiver? = null

    override fun onCreate() {
        // Create notification channel
        val notificationChannel = NotificationChannel("HeadsetStateChannel", "HeadsetStateChannel", NotificationManager.IMPORTANCE_DEFAULT)
        notificationChannel.description = "Headset State Notification Channel for Yin"
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(notificationChannel)
        // Register BroadcastReceiver
        headsetStateBroadcastReceiver = HeadsetStateBroadcastReceiver()
        val intentFilter = IntentFilter()
        intentFilter.addAction(Intent.ACTION_HEADSET_PLUG)
        registerReceiver(headsetStateBroadcastReceiver, intentFilter)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    override fun onDestroy() {
        unregisterReceiver(headsetStateBroadcastReceiver)
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }
}
