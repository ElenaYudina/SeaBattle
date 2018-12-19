package com.elena.SeaBattle.logic;

import com.elena.SeaBattle.entity.*;

import java.util.Scanner;

public class Input {
    Scanner scan;
    Playerable listener;

    public Input(Playerable listener) {
        scan = new Scanner(System.in);
        this.listener = listener;
    }

    public String process(){
        String in = scan.nextLine();
        boolean quiteGame = ("q".equals(in) || "quite".equals(in) || "3".equals(in) || "exit".equals(in));

        if (listener != null){
            if(quiteGame){
                listener.quiteGame();
                return in;
            }
        }
        return in;
    }

    private Cell parseCoords(String in){
        char[] coords = in.toCharArray();
        int x = -1;
        int y = -1;

        if (coords.length >= 2) {
            char symbol = Character.toLowerCase(coords[0]);
            x = symbol - 'a'; //a - 98 --> 0 - 9
            y = Integer.parseInt(String.valueOf(coords[1]));
        }
        return new Cell(x,y);
    }

    public int parseCoordX(String in){
        char[] coords = in.toCharArray();
        int x = -1;
        if (coords.length >= 2) {
            char symbol = Character.toLowerCase(coords[0]);
            x = symbol - 'a'; //a - 98 --> 0 - 9
        }
        return x;
    }

    public int parseCoordY(String in){
        char[] coords = in.toCharArray();
        int y = -1;
        if (coords.length >= 2) {
            y = Integer.parseInt(String.valueOf(coords[1]));
        }
        return y;
    }

    public Orientation parseOrientation(String in){
        Orientation orient = Orientation.Horizontal;
        if ("H".equals(in) || "h".equals(in)){
            orient = Orientation.Horizontal;
        }else if ("V".equals(in) || "v".equals(in)){
            orient = Orientation.Vertical;
        }
        return orient;
    }
}
