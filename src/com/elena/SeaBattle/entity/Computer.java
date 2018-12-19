package com.elena.SeaBattle.entity;

import java.util.Random;

public class Computer extends Player{
    private Random rand;
    private ComputerAttack attack;

    public Computer(Game game) {
        super(game);
        rand = new Random();
        this.attack = new ComputerAttackRandom(this);
     }

    public Random getRand() {
        return rand;
    }

    public void setAttack(ComputerAttack attack) {
        this.attack = attack;
    }

    public ComputerAttack getAttack() {
        return attack;
    }

    public Shot doShot() {
        return attack.doShot();
    }

    @Override
    public void process(){
        super.process();
        Shot shot;
        do {
            System.out.print("Выстрел компьютера: ");
            shot = doShot();
            System.out.println(" " + shot.toString());
        }while (shot != Shot.shotMissed || enemy.battleField.getCntLiveShips() == 0);
        game.nextStep();
        if (enemy.battleField.getCntLiveShips() == 0) {
            game.setGameOver();
            gameView.battleShow();
            System.out.println("Победил компьютер!");
        }
    }
}
