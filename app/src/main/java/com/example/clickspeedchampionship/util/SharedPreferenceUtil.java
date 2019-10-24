package com.example.clickspeedchampionship.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceUtil {
    private static SharedPreferenceUtil SINGLE_INSTANCE = null;
    private SharedPreferences sharedPreferences;

    private SharedPreferenceUtil(Context context){
        sharedPreferences = context.getSharedPreferences("com.example.clickspeedchampionship.sharedPreference", Context.MODE_PRIVATE);
    }

    public static SharedPreferenceUtil getInstance(Context context){
        if (SINGLE_INSTANCE == null){
            synchronized (SharedPreferenceUtil.class){
                SINGLE_INSTANCE = new SharedPreferenceUtil(context);
            }
        }
        return SINGLE_INSTANCE;
    }

    void setData(String name_player_1, String name_player_2, String selectedOptionValue){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name_player_1", name_player_1);
        editor.putString("name_player_2", name_player_2);
        editor.putString("selectedOptionValue", selectedOptionValue);
        editor.apply();
    }

    public String getDataString(String valueName){
        return sharedPreferences.getString(valueName, "");
    }

    void setState(boolean switchState){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("switchState", switchState);
        editor.apply();
    }

    public boolean getState(String value){
        return sharedPreferences.getBoolean(value, false);
    }

    public void setHighScore(int highScore){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("highScore", highScore);
        editor.apply();
    }

    public int getHighScore(){
        return sharedPreferences.getInt("highScore", 0);
    }
}
