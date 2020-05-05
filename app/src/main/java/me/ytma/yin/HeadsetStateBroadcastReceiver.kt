package me.ytma.yin

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class HeadsetStateBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        if (Intent.ACTION_HEADSET_PLUG == intent.action) {
            val state = intent.getIntExtra("state", 0)
            val name = intent.getStringExtra("name")
            val hasMicrophone = intent.getBooleanExtra("microphone", false)
            val builder = NotificationCompat.Builder(context, "HeadsetStateChannel")
            if (state > 0) {
                // Plugged
                builder.setSmallIcon(R.drawable.ic_headset_plug)
                    .setContentTitle("Headset plugged")
            } else {
                // Unplugged
                builder.setSmallIcon(R.drawable.ic_headset_unplug)
                    .setContentTitle("Headset unplugged")
            }
            NotificationManagerCompat.from(context).notify(0, builder.build())
        }
    }
}
