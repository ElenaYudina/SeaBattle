package com.elena.SeaBattle.entity;

public class PlaceShipCheck extends PlaceShip {

    public PlaceShipCheck(Ship ship) {
        super(ship);
    }

    @Override
    public boolean setShip(int x, int y) {
        if (getField().isValidCoord(x, y)){
            return getField().getCell(x, y).getState() == CellStates.cellWater;
        }
        else return false;
    }

    @Override
    public boolean setBorder(int x, int y) {
        return true;
    }
}
