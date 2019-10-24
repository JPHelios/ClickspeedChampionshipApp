package com.example.clickspeedchampionship.enums;

public enum ClickBasedGame {
    ENUM0(10),
    ENUM1(50),
    ENUM2(75),
    ENUM3(100),
    ENUM4(250),
    ENUM5(500),
    ENUM6(750),
    ENUM7(1000);

    private Integer clicks;

    ClickBasedGame(Integer clicks) {
        this.clicks = clicks;
    }

    @Override public String toString(){
        return clicks.toString();
    }

    public Integer getClicks() {
        return clicks;
    }
}
