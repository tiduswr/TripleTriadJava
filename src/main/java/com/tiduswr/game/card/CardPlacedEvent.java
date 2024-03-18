package com.tiduswr.game.card;

public record CardPlacedEvent(
    Card top,
    Card bottom,
    Card left,
    Card right,
    Card center
) {}
