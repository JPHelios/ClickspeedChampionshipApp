package com.example.clickspeedchampionship.ui.singleplayer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.clickspeedchampionship.R;
import com.example.clickspeedchampionship.ui.NameInputActivity;
import com.example.clickspeedchampionship.util.SharedPreferenceUtil;

public class WinnerSinglePlayerActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_winner_single_player);

        Intent intent = getIntent();
        SharedPreferenceUtil sharedPreferenceUtil = SharedPreferenceUtil.getInstance(getApplicationContext());
        int highScore = sharedPreferenceUtil.getHighScore();
        int clicks = intent.getIntExtra("clickNumber", 0);
        TextView clickNumberTextView = findViewById(R.id.clickNumber);
        TextView highScoreTextView = findViewById(R.id.highScore);
        Button againButton = findViewById(R.id.againButton);

        clickNumberTextView.setText(String.valueOf(clicks));
        highScoreTextView.setText("High Score: " + highScore);

        againButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NameInputActivity.class);
                startActivity(intent);
            }
        });


    }
}
