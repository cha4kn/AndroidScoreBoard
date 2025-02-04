package com.example.scoreboard;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.TextView;

public class ScoreBoardMain extends Activity {

    private TextView score1Text, score2Text;
    private int score1 = 0, score2 = 0;

    private static final String LOGGER_TAG = "ScoreBoardMain";

    private MediaButtonEventReceiver eventReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        score1Text = findViewById(R.id.guest_score);
        score2Text = findViewById(R.id.home_score);

        // Set a callback to handle media button events
        IntentFilter filter = new IntentFilter(Intent.ACTION_MEDIA_BUTTON);
        eventReceiver = new MediaButtonEventReceiver();
        //filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY); //this line sets receiver priority
        filter.setPriority(2139999999); //this line sets receiver priority
        registerReceiver(eventReceiver, filter, RECEIVER_EXPORTED);
    }

    /**
     * This method makes the volume buttons control the score.
     * @param keyCode The value in event.getKeyCode().
     * @param event Description of the key event.
     *
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.d(LOGGER_TAG, "Registered key!");
        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                incrementScore1();
                return true;
            case KeyEvent.KEYCODE_VOLUME_UP:
                incrementScore2();
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    // Method to increment the scores
    private void incrementScore1() {
        // Increment score1 or score2 based on your app's logic
        if (score1 == 99) {
            score1 = 0;
        } else {
            score1++;
        }

        // Update the displayed numbers
        score1Text.setText(String.format("%02d", score1));
    }

    private void incrementScore2() {
        // Increment score1 or score2 based on your app's logic
        if (score2 == 99) {
            score2 = 0;
        } else {
            score2++;
        }

        // Update the displayed numbers
        score2Text.setText(String.format("%02d", score2));
    }

    private void resetScores() {
        score1Text.setText(String.format("%02d", 0));
        score2Text.setText(String.format("%02d", 0));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(eventReceiver);
    }
}
