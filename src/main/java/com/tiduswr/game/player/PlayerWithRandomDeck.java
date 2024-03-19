package com.tiduswr.game.player;

import com.tiduswr.game.card.GameCards;
import com.tiduswr.game.card.RandomDeck;
import com.tiduswr.game.gui.CardColor;

public class PlayerWithRandomDeck extends Player{
    
    public PlayerWithRandomDeck(GameCards gameCards, String name, CardColor cardColor, PlayerPoints points){
        super(name, cardColor, points);
        this.deck = new RandomDeck(gameCards, this);
    }

}
