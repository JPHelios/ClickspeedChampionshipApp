package com.example.clickspeedchampionship.util;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.example.clickspeedchampionship.enums.ClickBasedGame;
import com.example.clickspeedchampionship.enums.GameMode;
import com.example.clickspeedchampionship.enums.SinglePlayerGame;
import com.example.clickspeedchampionship.enums.SinglePlayerTimeBased;
import com.example.clickspeedchampionship.enums.TimeBasedGame;
import com.example.clickspeedchampionship.ui.multiplayer.ClickBasedActivity;
import com.example.clickspeedchampionship.ui.singleplayer.SinglePlayerActivity;
import com.example.clickspeedchampionship.ui.multiplayer.TimeBasedActivity;

public class SpinnerUtil {

    private boolean switchState = false;
    private String gameModeOptionValue;
    String selectedOptionValue;

    public void setSpinner(final Spinner gameModeSpinner, final Spinner selectOpinionSpinner,
                           final Spinner singlePlayerModeSpinner, final Spinner singlePlayerSelectOption, final Context context,
                           final Switch switchMode, final TextView gameModeSelected,
                           Button button_to_game, final SharedPreferenceUtil sharedPreferenceUtil,
                           final EditText writeNamePlayer1, final EditText writeNamePlayer2, final TextView insertNamePlayer2) {


        gameModeSpinner.setAdapter(new ArrayAdapter<GameMode>(context,
                android.R.layout.simple_spinner_item, GameMode.values()));
        singlePlayerModeSpinner.setAdapter(new ArrayAdapter<SinglePlayerGame>(context,
                android.R.layout.simple_spinner_item, SinglePlayerGame.values()));
        singlePlayerSelectOption.setAdapter(new ArrayAdapter<SinglePlayerTimeBased>(context,
                android.R.layout.simple_spinner_item, SinglePlayerTimeBased.values()));


        switchMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                switchState = switchMode.isChecked();
                if (switchState) {
                    gameModeSelected.setText("Multiplayer");
                    gameModeSpinner.setVisibility(View.VISIBLE);
                    selectOpinionSpinner.setVisibility(View.VISIBLE);
                    singlePlayerModeSpinner.setVisibility(View.GONE);
                    singlePlayerSelectOption.setVisibility(View.GONE);
                    writeNamePlayer2.setVisibility(View.VISIBLE);
                    insertNamePlayer2.setVisibility(View.VISIBLE);


                } else {
                    gameModeSelected.setText("Singleplayer");
                    gameModeSpinner.setVisibility(View.GONE);
                    selectOpinionSpinner.setVisibility(View.GONE);
                    singlePlayerModeSpinner.setVisibility(View.VISIBLE);
                    singlePlayerSelectOption.setVisibility(View.VISIBLE);
                    writeNamePlayer2.setVisibility(View.GONE);
                    insertNamePlayer2.setVisibility(View.GONE);
                }
            }
        });

//      #############MULTIPLAYER GAME MODE SPINNER################
        gameModeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gameModeOptionValue = parent.getItemAtPosition(position).toString();

                if (gameModeOptionValue.equals(GameMode.TIME_BASED.toString())) {
                    selectOpinionSpinner.setAdapter(new ArrayAdapter<TimeBasedGame>(
                            context, android.R.layout.simple_spinner_item, TimeBasedGame.values()));
                } else {
                    selectOpinionSpinner.setAdapter(new ArrayAdapter<ClickBasedGame>(
                            context, android.R.layout.simple_spinner_item, ClickBasedGame.values()));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//      #############MULTIPLAYER VALUE OPTION SPINNER##############
        selectOpinionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedOptionValue = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//      #############SINGLEPLAYER GAME MODE############
        singlePlayerModeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gameModeOptionValue = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


//      #############SINGLEPLAYER VALUE OPTION SPINNER#############
        singlePlayerSelectOption.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedOptionValue = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        button_to_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name_player_1 = writeNamePlayer1.getText().toString();
                String name_player_2 = writeNamePlayer2.getText().toString();

                sharedPreferenceUtil.setState(switchState);

                if (gameModeOptionValue.equals(GameMode.CLICK_BASED.toString())) {
                    sharedPreferenceUtil.setData(name_player_1, name_player_2, selectedOptionValue);
                    Intent intent = new Intent(context, ClickBasedActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                } else if (gameModeOptionValue.equals(GameMode.TIME_BASED.toString())) {
                    sharedPreferenceUtil.setData(name_player_1, name_player_2, selectedOptionValue);
                    Intent intent = new Intent(context, TimeBasedActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                } else if (gameModeOptionValue.equals(SinglePlayerGame.SINGLE_PLAYER_TIME_BASED.toString())){
                    sharedPreferenceUtil.setData(name_player_1, name_player_2, selectedOptionValue);
                    Intent intent = new Intent(context, SinglePlayerActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            }
        });

    }

}
