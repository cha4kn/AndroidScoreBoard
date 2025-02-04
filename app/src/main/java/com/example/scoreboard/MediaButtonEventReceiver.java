package com.example.scoreboard;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;

class MediaButtonEventReceiver extends BroadcastReceiver {

    private static final String TAG = "MediaButtonEventReceiver";
    public MediaButtonEventReceiver() {
        super();
        Log.e(TAG, "Helloooo first!!");
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "Helloooo second!!"); // Changed from Log.e to Log.d for debugging

        KeyEvent event = intent.getParcelableExtra(Intent.EXTRA_KEY_EVENT);
        if (event == null) {
            return; // No key event data, return early
        }

        int action = event.getAction();
        if (action == KeyEvent.ACTION_DOWN) {
            Log.d(TAG, "onReceive code: " + action); // Debugging the action

            // Your logic to handle key press here (e.g., incrementing or decrementing score)
        }

        // Optionally call abortBroadcast() if you want to block other apps from receiving the event
        abortBroadcast();
    }
}