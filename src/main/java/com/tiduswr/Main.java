package com.tiduswr;

import java.io.IOException;

import com.tiduswr.game.CardGame;

public class Main {
    public static void main(String[] args) throws IOException {
        var cg = new CardGame();
        cg.start();
    }
}