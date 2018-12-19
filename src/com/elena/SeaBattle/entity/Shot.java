package com.elena.SeaBattle.entity;

public enum Shot {
    shotMissed(1),
    shotInjured(2),
    shotKilled(3);

    private final int value;

    private Shot(int value) {
        this.value=value;
    }

    @Override
    public String toString() {
        if (value == 1){
            return "промах";
        }
        else if(value == 2){
            return "ранен";
        }
        else if(value == 3){
            return "убит";
        }
        return "";
    }
}
