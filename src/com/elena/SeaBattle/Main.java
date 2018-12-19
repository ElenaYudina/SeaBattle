package com.elena.SeaBattle;

import com.elena.SeaBattle.entity.Game;

public class Main {

    public static void main(String[] args){
        Game game = new Game();
        if (!game.isGameOver()) game.start();
    }
}
