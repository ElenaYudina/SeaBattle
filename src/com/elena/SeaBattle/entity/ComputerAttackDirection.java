package com.elena.SeaBattle.entity;

import java.util.ArrayList;

public class ComputerAttackDirection extends ComputerAttack {

    public ComputerAttackDirection(Computer ai) {
        super(ai);
    }


    public void draw(ArrayList<Cell> list, int i, int j) {
        int m = x;
        int n = y;
        do {
            m+= i;
            n+= j;
        } while ( ai.enemy.battleField.isValidCoord(m, n) && (ai.enemy.battleField.getCell(m, n).getState() == CellStates.cellInjured) );

        if (ai.getBattleField().isValidCoord(m, n) ) {
            Cell cell = ai.enemy.battleField.getCell(m, n);
            if (!cell.isMark() ) {
                list.add(cell);
            }
        }
    }

    @Override
    public Shot doShot() {
        ArrayList<Cell> list = new ArrayList<Cell>();
        draw(list, dx, dy);
        draw(list, -dx, -dy);
        if (list.size() > 0) {
            Cell cell = list.get(ai.getRand().nextInt(list.size()));
            System.out.print("" + Character.toUpperCase((char)(cell.getX()+'a')) + cell.getY());
            return cell.doShot();
        }
        ai.setAttack(new ComputerAttackRandom(ai));
        return ai.doShot();
    }
}
