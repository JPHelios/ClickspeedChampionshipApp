package com.example.clickspeedchampionship.enums;

public enum SinglePlayerGame {
    SINGLE_PLAYER_TIME_BASED("Countdown Game");

    private String selectedGameMode;

    SinglePlayerGame(String selectedGameMode) {
        this.selectedGameMode = selectedGameMode;
    }

    @Override public String toString(){
        return selectedGameMode;
    }
}