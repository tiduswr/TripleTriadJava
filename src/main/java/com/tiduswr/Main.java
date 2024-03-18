package com.tiduswr;

import java.io.IOException;
import com.tiduswr.game.card.CardGame;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        var cg = new CardGame();
        cg.start();
    }
}