package com.elena.SeaBattle.entity;

import com.elena.SeaBattle.logic.BattleField;

import java.util.ArrayList;

public class ComputerAttackRandom extends ComputerAttack{
    public ComputerAttackRandom(Computer ai) {
        super(ai);
    }

    @Override
    public Shot doShot() {
        ArrayList<Cell> list = new ArrayList<Cell>();
        for (int j = 0; j < ai.enemy.battleField.getWidth(); j++) {
            for (int i = 0; i < ai.enemy.battleField.getHeight(); i++) {
                Cell e = ai.enemy.battleField.getCell(i, j);
                if (!e.isMark() ) {
                    list.add(e);
                }
            }
        }
        if (list.size() == 0) {
            return Shot.shotMissed;
        }
        Cell cell = list.get(ai.getRand().nextInt(list.size()));
        System.out.print("" + Character.toUpperCase((char)(cell.getX()+'a')) + cell.getY());
        Shot shot = cell.doShot();
        if (shot == Shot.shotInjured) {
            ai.setAttack(new ComputerAttackPlace(ai));
            ai.getAttack().setPosition(cell.getX(), cell.getY());
        }
        return shot;
    }

}
