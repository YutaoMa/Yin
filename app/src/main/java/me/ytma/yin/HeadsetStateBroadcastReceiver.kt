package me.ytma.yin

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class HeadsetStateBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        if (Intent.ACTION_HEADSET_PLUG == intent.action) {
            val state = intent.getIntExtra("state", 0)
            val hasMicrophone = intent.getIntExtra("microphone", 0)
            val builder = NotificationCompat.Builder(context, "HeadsetStateChannel")
            val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
            val volume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
            if (state > 0) {
                // Plugged
                if (hasMicrophone == 1) {
                    builder.setSmallIcon(R.drawable.ic_headset_with_microphone_plug)
                        .setContentTitle("Headset with microphone plugged")
                        .setContentText("Current volume level: $volume")
                } else {
                    builder.setSmallIcon(R.drawable.ic_headset_plug)
                        .setContentTitle("Headset plugged")
                        .setContentText("Current volume level: $volume")
                }
                NotificationManagerCompat.from(context).notify(0, builder.build())
            } else if (state == 0) {
                // Unplugged
                builder.setSmallIcon(R.drawable.ic_headset_unplug)
                    .setContentTitle("Headset unplugged")
                    .setContentText("Current volume level: $volume")
                NotificationManagerCompat.from(context).notify(0, builder.build())
            }
        }
    }
}
