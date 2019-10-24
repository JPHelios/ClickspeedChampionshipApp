package com.example.clickspeedchampionship.enums;


public enum TimeBasedGame {
    ENUM0(3),
    ENUM1(15),
    ENUM2(30),
    ENUM4(60),
    ENUM5(120),
    ENUM6(180);

    private Integer time;

    TimeBasedGame(Integer time) {
        this.time = time;
    }

    @Override public String toString(){
        return time.toString();
    }

    public Integer getTime() {
        return time;
    }
}
