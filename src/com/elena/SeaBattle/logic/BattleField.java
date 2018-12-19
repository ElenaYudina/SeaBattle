package com.elena.SeaBattle.logic;

import com.elena.SeaBattle.entity.Cell;
import com.elena.SeaBattle.entity.CellStates;
import com.elena.SeaBattle.entity.Ship;
import com.elena.SeaBattle.entity.Shot;

import java.util.ArrayList;

public class BattleField {
    private final int Width = 10;
    private final int Height = 10;
    private final int maxShip = 4;
    private Cell[][] cells;
    private ArrayList<Ship> ships;
    private int cntLiveShips;


    public BattleField() {
        cells = new Cell[Height][Width];
        setCntLiveShips(0);
        // заполняем поле водой
        for(int j = 0; j < Height; j++) {
            for(int i = 0; i < Width; i++) {
                cells[i][j] = new Cell(i, j);
            }
        }
        ships = new ArrayList<Ship>();
    }

    public void setRandomShips() {
        // заполняем поле кораблями
        for(int i = maxShip; i > 0; i--) {
            for(int j = maxShip-i+1; j > 0; j--) {
                Ship ship=new Ship(this,i);
                ships.add(ship);
            }
        }
        // удаляем окружение корабля
        for(int j = 0; j < Height; j++) {
            for(int i = 0; i < Width; i++) {
                Cell cell = cells[i][j];
                if (cell.getState() == CellStates.cellBorder) {
                    cell.setState(CellStates.cellWater);
                }
            }
        }
    }

    public Shot doShot(int x, int y) {
        Shot shot = cells[x][y].doShot();
        return shot;
    }

    public int getCntLiveShips() {
        return cntLiveShips;
    }

    public void setCntLiveShips(int cntLiveShips) {
        this.cntLiveShips = cntLiveShips;
    }

    public ArrayList<Ship> getShips() {
        return ships;
    }

    public int getWidth() {
        return Width;
    }

    public int getHeight() {
        return Height;
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public boolean isValidCoord(int x, int y){
        return x >=0 && x < Width && y >= 0 && y < Height;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public int getMaxShip() {
        return maxShip;
    }
}