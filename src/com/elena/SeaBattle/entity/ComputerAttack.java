package com.elena.SeaBattle.entity;

abstract class ComputerAttack {
    protected Computer ai;
    protected int x;
    protected int y;
    protected int dx;
    protected int dy;

    public ComputerAttack(Computer ai) {
        this.ai = ai;
    }

    abstract Shot doShot();

    protected void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    protected void setDirection(int x, int y) {
        this.dx = x;
        this.dy = y;
    }

}
