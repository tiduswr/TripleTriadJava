package com.tiduswr.game;

public class GameLogic implements BoardListeners{

    public GameLogic(){}

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
            other.cardOwner().setPlayer(placed.cardOwner().getPlayer());
        }
    }
    
}
