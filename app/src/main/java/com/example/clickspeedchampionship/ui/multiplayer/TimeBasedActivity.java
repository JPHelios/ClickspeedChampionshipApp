package com.example.clickspeedchampionship.ui.multiplayer;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.clickspeedchampionship.R;
import com.example.clickspeedchampionship.enums.GameMode;
import com.example.clickspeedchampionship.util.SharedPreferenceUtil;

public class TimeBasedActivity extends AppCompatActivity {

    private TextView countDownPlayer1_TextView;
    private TextView countDownPlayer2_TextView;

    private long timeLeftInMilliseconds;
    private int timeNumber;

    private int counterPlayer1 = 0;
    private TextView score_player_1;

    private int counterPlayer2 = 0;
    private TextView score_player_2;

    private TextView name_player_1;
    private TextView name_player_2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_time_based);

        SharedPreferenceUtil sharedPreferenceUtil = SharedPreferenceUtil.getInstance(getApplicationContext());


        countDownPlayer1_TextView = findViewById(R.id.timeCounter1);
        countDownPlayer2_TextView = findViewById(R.id.timeCounter2);

        score_player_1 = findViewById(R.id.score_player_1);
        score_player_2 = findViewById(R.id.score_player_2);

        RelativeLayout layout_player_1 = findViewById(R.id.layout_player_1);
        RelativeLayout layout_player_2 = findViewById(R.id.layout_player_2);

        name_player_1 = findViewById(R.id.name_player_1);
        name_player_2 = findViewById(R.id.name_player_2);

        //Get Player names and and time value
        String namePlayer1 = sharedPreferenceUtil.getDataString("name_player_1");
        String namePlayer2 = sharedPreferenceUtil.getDataString("name_player_2");
        timeNumber = Integer.valueOf(sharedPreferenceUtil.getDataString("selectedOptionValue"));

        timeLeftInMilliseconds = timeNumber * 1000 + 1000;
        startTimer();

        //Content check of player names, if no name was typed a default name is displayed automatically
        if (namePlayer1.length() > 0) {
            name_player_1.setText(namePlayer1);
        }

        if (namePlayer2.length() > 0) {
            name_player_2.setText(namePlayer2);
        }

        layout_player_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counterPlayer1++;
                score_player_1.setText(String.valueOf(counterPlayer1));
            }
        });

        layout_player_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counterPlayer2++;
                score_player_2.setText(String.valueOf(counterPlayer2));
            }
        });


    }

    public void startTimer() {
        CountDownTimer countDownTimer = new CountDownTimer(timeLeftInMilliseconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMilliseconds = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {
                if (counterPlayer1 > counterPlayer2) {
                    callWinnerActivity(name_player_1.getText().toString(), counterPlayer1);
                } else if (counterPlayer2 > counterPlayer1) {
                    callWinnerActivity(name_player_2.getText().toString(), counterPlayer2);
                } else {
                    callWinnerActivity("nobody", counterPlayer1);
                }
            }
        }.start();
    }

    private void callWinnerActivity(String winnerName, Integer clickAmount) {
        Intent intent = new Intent(TimeBasedActivity.this, WinnerActivity.class);
        intent.putExtra("winner_player", winnerName);
        intent.putExtra("clickNumber", clickAmount);
        intent.putExtra("GameMode", GameMode.TIME_BASED.name()); // GameMode.TIME_BASED
        intent.putExtra("timeNumber", timeNumber);
        intent.putExtra("NamePlayer1", name_player_1.getText().toString());
        intent.putExtra("NamePlayer2", name_player_2.getText().toString());
        startActivity(intent);
    }

    public void updateTimer() {
        int minutes = (int) timeLeftInMilliseconds / 60000;
        int seconds = (int) timeLeftInMilliseconds % 60000 / 1000;

        String timeLeftText;

        timeLeftText = " " + minutes;
        timeLeftText += ":";
        if (seconds < 10) {
            timeLeftText += "0";
        }
        timeLeftText += seconds;

        countDownPlayer1_TextView.setText(timeLeftText);
        countDownPlayer2_TextView.setText(timeLeftText);


    }
}