package com.example.tugaspraktikum5;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.widget.Toast;

import com.example.tugaspraktikum5.R;

public class Receiver extends BroadcastReceiver {
    MediaPlayer player;

    @Override
    public void onReceive(Context context, Intent intent){
        Toast.makeText(context, "We make a living by what we get, but we make a life by what we give.", Toast.LENGTH_LONG).show();
        player = MediaPlayer.create(context, R.raw.warning);
        player.start();
    }
}
