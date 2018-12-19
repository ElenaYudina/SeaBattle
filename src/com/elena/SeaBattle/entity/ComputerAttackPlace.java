package com.elena.SeaBattle.entity;

import com.elena.SeaBattle.logic.BattleField;

import java.util.ArrayList;

public class ComputerAttackPlace extends ComputerAttack {
    public ComputerAttackPlace(Computer ai) {
        super(ai);
    }

    @Override
    public Shot doShot() {
        int m, n;
        ArrayList<Cell> list = new ArrayList<Cell>();
        for(int i = 0; i < 2; i++) {
            m = x + i * 2 - 1;
            n = y;
            if (ai.enemy.battleField.isValidCoord(m, n) ) {
                Cell e = ai.enemy.battleField.getCell(m, n);
                if (! e.isMark() ) {
                    list.add(e);
                }
            }

            m = x;
            n = y + i * 2 - 1;
            if (ai.getBattleField().isValidCoord(m, n) ) {
                Cell e = ai.enemy.battleField.getCell(m, n);
                if (! e.isMark() ) {
                    list.add(e);
                }
            }
        }

        if (list.size() > 0) {
            Cell cell = list.get(ai.getRand().nextInt(list.size()));
            System.out.print("" + Character.toUpperCase((char)(cell.getX()+'a')) + cell.getY());
            Shot shot = cell.doShot();
            if (shot == Shot.shotInjured) {
                ai.setAttack(new ComputerAttackDirection(ai));
                ai.getAttack().setPosition(x, y);
                ai.getAttack().setDirection(cell.getX() - x, cell.getY() - y);
            }
            return shot;
        }

        ai.setAttack(new ComputerAttackRandom(ai));
        return ai.doShot();
    }
}
