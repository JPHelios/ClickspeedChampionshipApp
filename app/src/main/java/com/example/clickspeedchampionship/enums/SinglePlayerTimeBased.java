package com.example.clickspeedchampionship.enums;


public enum SinglePlayerTimeBased {
    ENUM1(10);

    private Integer time;

    SinglePlayerTimeBased(Integer time) {
        this.time = time;
    }

    @Override public String toString(){
        return time.toString();
    }

    public Integer getTime() {
        return time;
    }
}
