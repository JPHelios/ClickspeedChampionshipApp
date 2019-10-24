package com.example.clickspeedchampionship.enums;

public enum GameMode {
        CLICK_BASED("Click-based Game"),
        TIME_BASED("Time-based Game");


private String selectedGameMode;

        GameMode(String selectedGameMode) {
        this.selectedGameMode = selectedGameMode;
        }

@Override public String toString(){
        return selectedGameMode;
        }
        }