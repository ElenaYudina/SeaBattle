package com.elena.SeaBattle.entity;

import com.elena.SeaBattle.entity.Computer;
import com.elena.SeaBattle.entity.GameState;
import com.elena.SeaBattle.entity.Gamer;
import com.elena.SeaBattle.entity.Player;

import java.util.Scanner;

public class Game {
    private GameState state = GameState.Battle;
    private Player[] players;
    private int currentPlayer;
    private boolean isGameOver;

    public Game() {
        players = new Player[2];
        players[0] = new Gamer(this);
        players[1] = new Computer(this);
        isGameOver = false;
        newGame();
    }

    public void start(){
        do {
            process();
        }while (!isGameOver());

    }

    public void newGame() {
        System.out.println("Ввод координат в формате - a0");
        System.out.println("1 - Растановка кораблей случайным образом");
        System.out.println("2 - Ручной ввод местоположения кораблей (формат b1 h(v))");
        System.out.println("3 - Выйти из игры");
        String in = new Scanner(System.in).nextLine();
        if ("2".equals(in)) {
            state = GameState.Fill;
         }
        else if ("1".equals(in)) {
            players[0].battleField.setRandomShips();
        }
        else {
            isGameOver = true;
            return;
        }
        players[0].setEnemy(players[1]);
        players[1].battleField.setRandomShips();
        players[1].setEnemy(players[0]);
        currentPlayer = 0;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void nextStep(){
        currentPlayer++;
          if(currentPlayer >= players.length){
            currentPlayer = 0;
        }
    }

    private void process(){
        players[currentPlayer].process();
    }

    public GameState getState(){
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public void setGameOver(){
        isGameOver = true;
    }

    public boolean isGameOver(){
        return isGameOver;
    }
}
