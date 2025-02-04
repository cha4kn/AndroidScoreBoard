package com.example.scoreboard;

import android.app.Activity;
import android.content.Intent;
import android.media.session.MediaSession;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;

public class ScoreBoardMain extends Activity {

    private TextView score1Text, score2Text;
    private int score1 = 0, score2 = 0;

    private MediaSession mediaSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        score1Text = findViewById(R.id.guest_score);
        score2Text = findViewById(R.id.home_score);

        // Create a MediaSession to handle media button presses
        mediaSession = new MediaSession(this, "ScoreBoardMediaSession");

        // Set a callback to handle media button events
        mediaSession.setCallback(new MediaSession.Callback() {
            @Override
            public boolean onMediaButtonEvent(Intent mediaButtonIntent) {
                if (mediaButtonIntent != null && mediaButtonIntent.hasExtra(Intent.EXTRA_KEY_EVENT)) {
                    KeyEvent keyEvent = mediaButtonIntent.getParcelableExtra(Intent.EXTRA_KEY_EVENT);
                    if (keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                        // Check if it's the button you want to react to (headphone button or media play/pause)
                        if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_HEADSETHOOK) {
                            incrementScore1();
                        } else if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_MEDIA_NEXT) {
                            incrementScore2();
                        } else if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_MEDIA_PREVIOUS) {
                            resetScores();
                        }
                    }
                }
                return super.onMediaButtonEvent(mediaButtonIntent);
            }
        });

        // Activate the MediaSession
        mediaSession.setActive(true);
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
        // Deactivate the MediaSession when the activity is destroyed
        mediaSession.setActive(false);
    }
}
