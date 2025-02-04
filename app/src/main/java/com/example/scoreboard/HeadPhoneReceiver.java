package com.example.scoreboard;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.util.Log;

public class HeadPhoneReceiver extends BroadcastReceiver {
    public static final String ACTION_SCORE_UPDATE = "com.example.scoreboard.UPDATE_SCORE";
    public static final String EXTRA_SCORE_TYPE = "scoreType";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_MEDIA_BUTTON.equals(intent.getAction())) {
            KeyEvent keyEvent = intent.getParcelableExtra(Intent.EXTRA_KEY_EVENT);
            if (keyEvent != null && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                Intent scoreIntent = new Intent(ACTION_SCORE_UPDATE);
                if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_MEDIA_PREVIOUS) {
                    Log.d("HeadphoneReceiver", "Home score incremented");
                    scoreIntent.putExtra(EXTRA_SCORE_TYPE, "home");
                } else if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_MEDIA_NEXT) {
                    Log.d("HeadphoneReceiver", "Guest score incremented");
                    scoreIntent.putExtra(EXTRA_SCORE_TYPE, "guest");
                }
                context.sendBroadcast(scoreIntent);
            }
        }
    }
}
