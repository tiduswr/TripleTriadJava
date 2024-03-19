package com.tiduswr.game.player;

import com.tiduswr.game.card.Deck;
import com.tiduswr.game.gui.CardColor;

public class Player{
    protected Deck deck;
    protected final String name;
    protected final CardColor cardColor;
    protected final PlayerPoints points;

    protected Player(String name, CardColor cardColor, PlayerPoints points){
        this.name = name;
        this.cardColor = cardColor;
        this.points = points;
    }

    public Player(Deck deck, String name, CardColor cardColor, PlayerPoints points){
        this.deck = deck;
        this.name = name;
        this.cardColor = cardColor;
        this.points = points;
    }

    public CardColor getCardColor() {
        return cardColor;
    }

    public Deck getDeck() {
        return deck;
    }

    public String getName() {
        return name;
    }

    public PlayerPoints getPoints() {
        return points;
    }

}
