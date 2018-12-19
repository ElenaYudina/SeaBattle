package com.elena.SeaBattle.entity;

public class GameView {
    private Game game;
    private Player[] players;

    public GameView(Game game) {
        this.game = game;
        players = game.getPlayers();
    }

    public void battleShow(){
        char [] head = new char [] {'A','B','C','D','E','F','G','H','I','J'};
        int H=players[0].battleField.getHeight();
        int W=players[0].battleField.getWidth();;
        System.out.println("        Gamer"  + "                        Computer");
        System.out.print("   ");
        for (int n = 0; n < 2; n++) {
            for (int i = 0; i < W; i++) {
                System.out.print(head[i]);
                System.out.print(" ");
            }
            System.out.print("          ");
        }
        System.out.println();
        for (int j = 0; j < H; j++) {
            for (int n = 0; n < 2; n++) {
                System.out.printf("%2d",j);
                for (int i = 0; i < W; i++) {
                    System.out.print(' ');
                    if (n == 1 && players[n].battleField.getCells()[i][j].getState() == CellStates.cellWell){
                        System.out.print(CellStates.cellWater.toString());
                    }
                    else {
                        System.out.print(players[n].battleField.getCells()[i][j].toString());
                    }

                }
                System.out.print('|');
                if (n==0) System.out.print("       ");
            }
            System.out.println("");
        }
    }
}
