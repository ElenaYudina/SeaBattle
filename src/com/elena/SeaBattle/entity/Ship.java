package com.elena.SeaBattle.entity;

import com.elena.SeaBattle.logic.BattleField;

import java.util.ArrayList;
import java.util.Random;

public class Ship {
    private int x;
    private int y;
    private int dx;
    private int dy;
    private int length;
    private int health;
    private Orientation orientation;
    private BattleField battleField;
    private ArrayList<Cell> cells;
    private ArrayList<Cell> borders;
    private ShipStates state;

    public Ship(BattleField battleField, int length) {
        this.length = length;
        this.health = length;
        this.battleField = battleField;
        this.state = ShipStates.shipWell;

        do {
            getPlace();
        } while (!checkPlace());

        this.cells = new ArrayList<Cell>();
        this.borders = new ArrayList<Cell>();
        this.setShip();
        getBattleField().setCntLiveShips(getBattleField().getCntLiveShips() + 1);
    }

    public Ship(BattleField battleField, int length, Orientation orientation, int x, int y) {
        this.length = length;
        this.health = length;
        this.battleField = battleField;
        this.x = x;
        this.y = y;
        this.state = ShipStates.shipWell;
        this.orientation = orientation;
        if (orientation == Orientation.Horizontal) {
            this.dx = 1;
        } else {
            this.dy = 1;
        }
        this.cells = new ArrayList<Cell>();
        this.borders = new ArrayList<Cell>();
    }
    private void getPlace() {
        Random rand = new Random();
        this.x = rand.nextInt(getBattleField().getWidth());
        this.y = rand.nextInt(getBattleField().getHeight());
        this.dx = 0;
        this.dy = 0;
        if (rand.nextInt(2) == 1) {
            this.dx = 1;
        } else {
            this.dy = 1;
        }
    }

    private boolean bypass(PlaceShip tp) {
        int i, m, n;

        // корабль
        for(i = 0; i < length; i++) {
            m = y+i*dy;
            n = x+i*dx;
            if (!tp.setShip(n,m)) {
                return false;
            }
        }
        // площадка сверху и снизу корабля
        for(i=0; i < length; i++) {
            m = y+i*dy-dx;
            n = x+i*dx-dy;
            if (!tp.setBorder(n, m)) {
                return false;
            }
            m = y+i*dy+dx;
            n = x+i*dx+dy;
            if (!tp.setBorder(n, m)) {
                return false;
            }
        }
        // площадка слева и справа корабля
        for(i = -1; i < 2; i++) {
            m = y+i*dx-dy;
            n = x+i*dy-dx;
            if (!tp.setBorder(n, m)) {
                return false;
            }
            m = y+i*dx+(dy*length);
            n = x+i*dy+(dx*length);
            if (!tp.setBorder(n, m)) {
                return false;
            }
        }
        return true;
    }

    public boolean checkPlace() {
        return bypass(new PlaceShipCheck(this));
    }

    public void setCell(Cell cell) {
        cells.add(cell);
    }

     // Установка на поле корабля и его окружения
    public void setShip() {
        bypass(new PlaceShipSet(this));
    }

    public Shot doShot() {
        if (health != 0) {
            health--;
            if (health == 0) {
                getBattleField().setCntLiveShips(getBattleField().getCntLiveShips() - 1);
                state = ShipStates.shipKilled;
                for(Cell e : cells) {
                    e.setState(CellStates.cellKilled);

                }
                for(Cell e : borders) {
                    e.setState(CellStates.cellMissed);
                    e.setMark(true);
                }
                return Shot.shotKilled;
            } else {
                state = ShipStates.shipInjured;
            }
        }
        return Shot.shotInjured;
    }

    public BattleField getBattleField() {
        return battleField;
    }

    public ArrayList<Cell> getCells() {
        return cells;
    }

    public ArrayList<Cell> getBorders() {
        return borders;
    }

    public int getLength() {
        return length;
    }

    public ShipStates getState() {
        return state;
    }
 }
