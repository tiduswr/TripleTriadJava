package com.tiduswr.game.board;

import com.tiduswr.game.card.CardPlacedEvent;

public interface BoardListeners {
    void update(CardPlacedEvent cardPlacedEvent);
}
