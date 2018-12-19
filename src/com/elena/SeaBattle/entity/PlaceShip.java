package com.elena.SeaBattle.entity;

import com.elena.SeaBattle.logic.BattleField;

public abstract class PlaceShip {
    private BattleField field;

    public PlaceShip(Ship ship) {
        this.field = ship.getBattleField();
    }

    public BattleField getField() {
        return field;
    }

    abstract public boolean setShip(int m, int n);
    abstract public boolean setBorder(int m, int n);
}
