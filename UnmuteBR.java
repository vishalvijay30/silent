package com.example.vishal.silent;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.widget.Toast;

/**
 * Created by kiran on 9/24/2016.
 */
public class UnmuteBR extends BroadcastReceiver {
    @Override

    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context,"unmuting", Toast.LENGTH_SHORT).show();
        AudioManager audio = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);

        audio.setRingerMode(AudioManager.RINGER_MODE_NORMAL);


    }
}
