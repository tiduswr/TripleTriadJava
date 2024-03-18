package com.tiduswr.game;

import com.tiduswr.game.card.CardPlacedEvent;

public interface BoardListeners {
    void update(CardPlacedEvent cardPlacedEvent);
}
