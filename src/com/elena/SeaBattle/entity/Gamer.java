package com.elena.SeaBattle.entity;

import com.elena.SeaBattle.logic.Input;

public class Gamer extends Player implements Playerable{
    private Input input;

    public Gamer(Game game) {
        super(game);
        input = new Input(this);
    }

    public void setShips() {
        Ship ship;
        // заполняем поле кораблями
        for(int i = battleField.getMaxShip(); i > 0; i--) {
            for(int j = battleField.getMaxShip()-i+1; j > 0; j--) {
                int k=0;
                while (k==0 && !game.isGameOver()) {
                    System.out.println("Ведите местоположение " + i + "-х палубного корабля:");
                    String in = input.process();
                    if (game.isGameOver()) return;
                    int x = input.parseCoordX(in);
                    int y = input.parseCoordY(in);
                    Orientation o = Orientation.Horizontal;
                    if (i!=1) {
                        System.out.println("Ведите ориентацию " + i + "-х палубного корабля (горизонтальная - h или вертикальная - v):");
                        o = input.parseOrientation(input.process());
                    }
                    if (game.isGameOver()) return;
                    ship = new Ship(battleField, i, o, x, y);
                    if (ship.checkPlace()){
                        ship.setShip();
                        battleField.setCntLiveShips(battleField.getCntLiveShips() + 1);
                        k=k+1;
                        battleField.getShips().add(ship);
                    }
                }
                super.process();
            }
        }
        // удаляем окружение корабля
        for(int j = 0; j < battleField.getHeight(); j++) {
            for(int i = 0; i < battleField.getWidth(); i++) {
                Cell cell = battleField.getCells()[i][j];
                if (cell.getState() == CellStates.cellBorder) {
                    cell.setState(CellStates.cellWater);
                }
            }
        }
    }

    public void attack(int x, int y){
        if (enemy.battleField.getCell(x, y).isMark()) {
            return;
        }
        if (enemy.battleField.doShot(x, y) == Shot.shotMissed) {
             game.nextStep();
        }
        if (enemy.battleField.getCntLiveShips() == 0) {
            game.setGameOver();
            gameView.battleShow();
            System.out.println("Поздравляю, вы победили!");
        }
    }

    @Override
    public void quiteGame() {
        game.setGameOver();
    }

    @Override
    public void process(){
        super.process();
        if (game.getState() == GameState.Fill){
            setShips();
            game.setState(GameState.Battle);
        }else if (game.getState() == GameState.Battle){
            System.out.print("Ваш выстрел: ");
            String in = input.process();
            int x = input.parseCoordX(in);
            int y = input.parseCoordY(in);
            if (enemy.battleField.isValidCoord(x, y)) {
                attack(x, y);
            }
        }
    }
}
