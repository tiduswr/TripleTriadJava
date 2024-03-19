package com.tiduswr.game.card;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    
    protected final List<Card> CARDS;
    protected final short MAX_SIZE = 5;

    public Deck(){
        CARDS = new ArrayList<>();
    }

    public int getMaxSize(){
        return MAX_SIZE;
    }

    public List<Card> cards(){
        return CARDS;
    }

    public int handSize(){
        return CARDS.size();
    }

    public void addCard(Card c){
        if(CARDS.size() < MAX_SIZE)
            CARDS.add(c);
    }

    public Card checkCardByIndex(int index){
        return CARDS.get(index);
    }

    public Card retrieveCardByIndex(int index){
        return CARDS.remove(index);
    }

}
