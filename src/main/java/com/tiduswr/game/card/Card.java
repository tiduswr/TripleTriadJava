package com.tiduswr.game.card;

import com.tiduswr.game.player.Player;

public record Card(
    String name,
    int attackTop, 
    int attackBottom, 
    int attackLeft, 
    int attackRight,
    CardOwner cardOwner
) {
    public Card copy(Player p) {
        return new Card(name, attackTop, attackBottom, attackLeft, attackRight, new CardOwner(p));
    }
}