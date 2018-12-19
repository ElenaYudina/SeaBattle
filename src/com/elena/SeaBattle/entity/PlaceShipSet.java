package com.elena.SeaBattle.entity;

public class PlaceShipSet extends PlaceShip {
    private Ship ship;

    public PlaceShipSet(Ship ship) {
        super(ship);
        this.ship = ship;
    }

    @Override
    public boolean setShip(int x, int y) {
        getField().getCell(x, y).setState(CellStates.cellWell);
        ship.getCells().add(getField().getCell(x, y));
        getField().getCell(x, y).setShip(ship);
        return true;
    }

    @Override
    public boolean setBorder(int x, int y) {
        if ( getField().isValidCoord(x, y) ) {
            getField().getCell(x, y).setState(CellStates.cellBorder);
            ship.getBorders().add(getField().getCell(x, y));
        }
        return true;
    }
}

