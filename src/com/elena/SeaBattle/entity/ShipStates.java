package com.elena.SeaBattle.entity;

public enum ShipStates {
    shipWell(1),
    shipInjured(2),
    shipKilled(3);

    private final int value;

    ShipStates(int value) {
        this.value = value;
    }
}
