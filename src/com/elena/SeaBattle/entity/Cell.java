package com.elena.SeaBattle.entity;

import com.elena.SeaBattle.logic.BattleField;

public class Cell {
    private int x;
    private int y;
    private CellStates state;
    private Ship ship;
    private boolean mark;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setMark(boolean mark) {
        this.mark = mark;
    }

    public boolean isMark() {
        return mark;
    }

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.state=CellStates.cellWater;
    }

    @Override
    public String toString() {
        if (this.state==CellStates.cellWater){
            return "`";
        }
        else if(this.state==CellStates.cellWell){
            return "O";
        }
        else if(this.state==CellStates.cellInjured || this.state==CellStates.cellKilled){
            return "X";
        }
        else if(this.state==CellStates.cellMissed || this.state==CellStates.cellBorder){
            return "*";
        }
        return ".";
    }

    public CellStates getState() {
        return state;
    }

    public void setState(CellStates state) {
        this.state = state;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public Shot doShot() {
        mark = true;
        if (state == CellStates.cellWell) {
            setState(CellStates.cellInjured);
            return getShip().doShot();
        } else {
            if ( (state == CellStates.cellBorder) || (state == CellStates.cellWater)) {
                setState(CellStates.cellMissed);
            }
        }
        return Shot.shotMissed;
    }

}
