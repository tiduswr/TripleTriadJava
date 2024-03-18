package com.tiduswr.game;

import com.tiduswr.game.gui.CardColor;

public record Player(
    Deck deck,
    String name,
    CardColor cardColor,
    PlayerPoints points
){}
