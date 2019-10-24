package com.example.clickspeedchampionship.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.example.clickspeedchampionship.R;
import com.example.clickspeedchampionship.util.SharedPreferenceUtil;
import com.example.clickspeedchampionship.util.SpinnerUtil;

public class NameInputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_name_input);

        Button button_to_game = findViewById(R.id.button_to_game);
        EditText writeNamePlayer1 = findViewById(R.id.writeNamePlayer1);
        EditText writeNamePlayer2 = findViewById(R.id.writeNamePlayer2);
        final Spinner gameMode_Spinner = findViewById(R.id.selectGameMode);
        Spinner singlePlayerModeSpinner = findViewById(R.id.singlePlayerModeSpinner);
        Spinner selectOption_Spinner = findViewById(R.id.selectOption);
        Spinner singlePlayerSelectOption = findViewById(R.id.singlePlayerSelectOption);

        Switch switchMode = findViewById(R.id.switchMode);
        TextView gameModeSelected = findViewById(R.id.gameModeSelected);
        SharedPreferenceUtil sharedPreferenceUtil = SharedPreferenceUtil.getInstance(getApplicationContext());
        SpinnerUtil spinnerUtil = new SpinnerUtil();
        switchMode.setChecked(sharedPreferenceUtil.getState("switchState"));
        TextView insertNamePlayer2 = findViewById(R.id.InsertNamePlayer2);

        writeNamePlayer1.setText(sharedPreferenceUtil.getDataString("name_player_1"));
        writeNamePlayer2.setText(sharedPreferenceUtil.getDataString("name_player_2"));

        if (!sharedPreferenceUtil.getState("switchState")){
            gameModeSelected.setText("Singleplayer");
            gameMode_Spinner.setVisibility(View.GONE);
            selectOption_Spinner.setVisibility(View.GONE);
            singlePlayerModeSpinner.setVisibility(View.VISIBLE);
            singlePlayerSelectOption.setVisibility(View.VISIBLE);
            writeNamePlayer2.setVisibility(View.GONE);
            insertNamePlayer2.setVisibility(View.GONE);
        } else{
            gameModeSelected.setText("Multiplayer");
            gameMode_Spinner.setVisibility(View.VISIBLE);
            selectOption_Spinner.setVisibility(View.VISIBLE);
            writeNamePlayer2.setVisibility(View.VISIBLE);
            insertNamePlayer2.setVisibility(View.VISIBLE);
            singlePlayerModeSpinner.setVisibility(View.GONE);
            singlePlayerSelectOption.setVisibility(View.GONE);
        }

        spinnerUtil.setSpinner(gameMode_Spinner, selectOption_Spinner, singlePlayerModeSpinner, singlePlayerSelectOption,
                getApplicationContext(), switchMode, gameModeSelected, button_to_game,
                sharedPreferenceUtil, writeNamePlayer1, writeNamePlayer2, insertNamePlayer2);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory(Intent.CATEGORY_HOME);
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);
    }
}
