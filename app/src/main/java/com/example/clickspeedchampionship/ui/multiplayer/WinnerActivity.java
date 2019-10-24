package com.example.clickspeedchampionship.ui.multiplayer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.clickspeedchampionship.R;
import com.example.clickspeedchampionship.enums.GameMode;
import com.example.clickspeedchampionship.ui.NameInputActivity;

import java.util.Objects;

public class WinnerActivity extends AppCompatActivity {

    private String NamePlayer1;
    private String NamePlayer2;


    @SuppressLint({"WrongViewCast", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_winner);

        TextView winnerNameTextView = findViewById(R.id.winner);
        TextView clickNumber_TextView = findViewById(R.id.amountOfClicks);
        Button rematch = findViewById(R.id.rematch);

        Intent intent = getIntent();
        String nameWinner = intent.getStringExtra("winner_player");
        int clickNumber = intent.getIntExtra("clickNumber", 0) + 1;
        String game_mode = intent.getStringExtra("GameMode");
        int time = intent.getIntExtra("timeNumber", 30);
        NamePlayer1 = intent.getStringExtra("NamePlayer1");
        NamePlayer2 = intent.getStringExtra("NamePlayer2");

        if (Objects.equals(game_mode, GameMode.TIME_BASED.name())){
            winnerNameTextView.setText(nameWinner + " with " + (clickNumber - 1) + " Clicks");
            clickNumber_TextView.setText(time + " Seconds");
        } else {
            winnerNameTextView.setText(nameWinner);
            clickNumber_TextView.setText(clickNumber + " Clicks");
        }

        rematch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WinnerActivity.this, NameInputActivity.class);
                intent.putExtra("NamePlayer1", NamePlayer1);
                intent.putExtra("NamePlayer2", NamePlayer2);
                startActivity(intent);
            }
        });
    }
}
