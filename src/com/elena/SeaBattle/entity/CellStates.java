package com.elena.SeaBattle.entity;

public enum CellStates {
    cellWater(1),
    cellBorder(2),
    cellWell(3),
    cellInjured(4),
    cellKilled(5),
    cellMissed(6);

    private final int value;

    @Override
    public String toString() {
        if (value == 1){
            return "`";
        }
        else if(value == 3){
            return "O";
        }
        else if((value == 4) || (value == 5 )){
            return "X";
        }
        else if((value == 6) || (value == 2)){
            return "*";
        }
        return ".";
    }

    private CellStates(int value) {
        this.value=value;
    }

    public int getValue(){
        return value;
    }
}
