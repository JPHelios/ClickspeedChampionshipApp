package com.example.clickspeedchampionship.ui.animation;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.clickspeedchampionship.R;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreenActivity.this, LoadingStartActivity.class);
                startActivityForResult(i, 1);

                finish();
            }
        }, 5000);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        finish();
    }

}

//Further projects:
// -GameMode: (SingelPlayer) TimeRace
//        you have e.g. 30 to click 50 times, if you got it: time will increased for the next round by e.g. 5 sec
// -Application: (MultiPlayer/SingelPlayer) HighscoreTable to safe the best games (time-based-Game and TimeRace)
