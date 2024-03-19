package com.tiduswr.game.card;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class GameCards {
    private final String CARDS_FILE;
    private final List<Card> CARDS;

    public GameCards() throws IOException{
        CARDS_FILE = "Cards.csv";
        CARDS = new ArrayList<>();
        loadCards();
    }

    public List<Card> getCards(){
        return CARDS;
    }

    private void loadCards() throws IOException{
        var is = this.getClass().getClassLoader().getResourceAsStream(CARDS_FILE);
        try(BufferedReader buffer = new BufferedReader(new InputStreamReader(is))){
            String line;
            while((line = buffer.readLine()) != null){
                String[] row = line.split(",");

                Card card = new Card(row[0], 
                    Integer.parseInt(row[1]), 
                    Integer.parseInt(row[2]), 
                    Integer.parseInt(row[4]), 
                    Integer.parseInt(row[3]),
                    new CardOwner(null)
                );
                CARDS.add(card);
            }
        }
    }

}
