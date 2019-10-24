package com.example.clickspeedchampionship.ui.singleplayer;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.clickspeedchampionship.R;
import com.example.clickspeedchampionship.util.SharedPreferenceUtil;

public class SinglePlayerActivity extends AppCompatActivity {

    private TextView counter_TextView;
    private TextView scorePlayer_TextView;
    private SharedPreferenceUtil sharedPreferenceUtil;
    private long timeLeftInMilliseconds;
    private long timeLeft;
    private int clickNumber = 0;
    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_single_player);

        sharedPreferenceUtil = SharedPreferenceUtil.getInstance(getApplicationContext());

        counter_TextView = findViewById(R.id.timeCounter_TextView);
        scorePlayer_TextView = findViewById(R.id.scorePlayer_TextView);
        TextView name_player = findViewById(R.id.name_player);
        RelativeLayout layout_player = findViewById(R.id.layout_player);

        String name = sharedPreferenceUtil.getDataString("name_player_1");
        name_player.setText(name);
        int timeNumber = Integer.valueOf(sharedPreferenceUtil.getDataString("selectedOptionValue"));

        timeLeftInMilliseconds = timeNumber * 1000 + 1000;

        startTimer();

        layout_player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickNumber++;
                scorePlayer_TextView.setText(String.valueOf(clickNumber));

//                if(clickNumber%20 == 0){
//                    timeLeftInMilliseconds = millisUntilFinished + 5000;
//                }
            }
        });
    }



    public void startTimer(){
         countDownTimer = new CountDownTimer(timeLeftInMilliseconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMilliseconds = millisUntilFinished;


                updateTimer();
            }

            @Override
            public void onFinish() {
                callHighscore(clickNumber);
            }
        }.start();
    }

    public void updateTimer(){

        int minute = (int) timeLeftInMilliseconds / 60000;
        int seconds = (int) timeLeftInMilliseconds % 60000 / 1000;

        String timeLeftText;

        timeLeftText = " " + minute;
        timeLeftText += ":";
        if (seconds < 10) {
            timeLeftText += "0";
        }
        timeLeftText += seconds;

        counter_TextView.setText(timeLeftText);
    }

    public void callHighscore(int clickNumber){
        int highScore = sharedPreferenceUtil.getHighScore();

        if (highScore < clickNumber){
            sharedPreferenceUtil.setHighScore(clickNumber);
        }

        Intent intent = new Intent(getApplicationContext(), WinnerSinglePlayerActivity.class);
        intent.putExtra("clickNumber", clickNumber);
        startActivity(intent);
    }
}
