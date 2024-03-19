package com.tiduswr.game;

import com.tiduswr.game.board.BoardListeners;
import com.tiduswr.game.card.Card;
import com.tiduswr.game.card.CardPlacedEvent;

public class GameLogic implements BoardListeners{

    @Override
    public void update(CardPlacedEvent cardPlacedEvent) {
        var placed = cardPlacedEvent.center();
        var t = cardPlacedEvent.top();
        var b = cardPlacedEvent.bottom();
        var l = cardPlacedEvent.left();
        var r = cardPlacedEvent.right();

        if(t != null)
            checkAndSwap(placed, t, placed.attackTop(), t.attackBottom());
        if(b != null)
            checkAndSwap(placed, b, placed.attackBottom(), b.attackTop());
        if(l != null)
            checkAndSwap(placed, l, placed.attackLeft(), l.attackRight());
        if(r != null)
            checkAndSwap(placed, r, placed.attackRight(), r.attackLeft());
    }

    private void checkAndSwap(Card placed, Card other, int atkPlaced, int atkOther){
        if(atkPlaced > atkOther){
            var prevOwner = other.cardOwner().getPlayer();
            var newOwner = placed.cardOwner().getPlayer();
            
            prevOwner.points().removePoint();
            newOwner.points().addPoint();

            other.cardOwner().setPlayer(newOwner);
        }
    }
    
}
