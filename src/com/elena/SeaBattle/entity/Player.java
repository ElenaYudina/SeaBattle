package com.elena.SeaBattle.entity;

import com.elena.SeaBattle.logic.BattleField;

public class Player {
    protected Game game;
    protected BattleField battleField;
    protected GameView gameView;
    protected Player enemy;

    public Player(Game game) {
        this.game = game;
        battleField = new BattleField();
        gameView = new GameView(game);
    }

    public void setEnemy(Player enemy) {
        this.enemy = enemy;
    }

    public BattleField getBattleField() {
        return battleField;
    }

    public void setShips() {}

    public Player getEnemy() {
        return enemy;
    }

    public void process(){
        gameView.battleShow();
    }
}
