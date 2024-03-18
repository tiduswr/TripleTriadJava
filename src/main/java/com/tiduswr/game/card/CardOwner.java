package com.tiduswr.game.card;

import com.tiduswr.game.Player;

public class CardOwner {
    
    private Player p;

    public CardOwner(Player p){
        this.p = p;
    }

    public void setPlayer(Player p) {
        this.p = p;
    }

    public Player getPlayer() {
        return p;
    }

}
