package com.example.clickspeedchampionship.ui.multiplayer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.clickspeedchampionship.R;
import com.example.clickspeedchampionship.enums.GameMode;
import com.example.clickspeedchampionship.util.SharedPreferenceUtil;

// android:fullBackupContent="true" automatically added to AndroidManifest.xml -> maybe delete

public class ClickBasedActivity extends AppCompatActivity {

    private Integer counterPlayer1 = 0;
    private TextView score_player_1;
    private Integer counterPlayer2 = 0;
    private TextView score_player_2;
    private int maxClicks;
    private TextView name_player_1;
    private TextView name_player_2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_click_based);

        score_player_1 = findViewById(R.id.score_player_1);
        score_player_2 = findViewById(R.id.score_player_2);

        RelativeLayout layout_player_1 = findViewById(R.id.layout_player_1);
        RelativeLayout layout_player_2 = findViewById(R.id.layout_player_2);

        name_player_1 = findViewById(R.id.name_player_1);
        name_player_2 = findViewById(R.id.name_player_2);

        SharedPreferenceUtil sharedPreferenceUtil = SharedPreferenceUtil.getInstance(getApplicationContext());
        String namePlayer1 = sharedPreferenceUtil.getDataString("name_player_1");
        String namePlayer2 = sharedPreferenceUtil.getDataString("name_player_2");
        maxClicks = Integer.valueOf(sharedPreferenceUtil.getDataString("selectedOptionValue")) - 1;




        //Content check of player names, if no name was typed a default name is displayed automatically
        if (namePlayer1.length() > 0) {
            name_player_1.setText(namePlayer1);
        }

        if (namePlayer2.length() > 0) {
            name_player_2.setText(namePlayer2);
        }


        //Counter for clicks
        layout_player_1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (counterPlayer1 < maxClicks) {
                    counterPlayer1++;
                    score_player_1.setText(String.valueOf(counterPlayer1));
                } else {
                    showWinner(name_player_1.getText().toString());
                }
            }
        });

        layout_player_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counterPlayer2 < maxClicks) {
                    counterPlayer2++;
                    score_player_2.setText(String.valueOf(counterPlayer2));
                } else {
                    showWinner(name_player_2.getText().toString());
                }
            }
        });

    }

    private void showWinner(String winnerName) {
        Intent intent = new Intent(ClickBasedActivity.this, WinnerActivity.class);
        intent.putExtra("winner_player", winnerName);
        intent.putExtra("clickNumber", maxClicks);
        intent.putExtra("GameMode", GameMode.CLICK_BASED.name());
        intent.putExtra("NamePlayer1", name_player_1.getText().toString());
        intent.putExtra("NamePlayer2", name_player_2.getText().toString());
        startActivity(intent);
    }


}
