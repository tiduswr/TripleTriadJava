package com.tiduswr.game;

public record CardPlacedEvent(
    Card top,
    Card bottom,
    Card left,
    Card right,
    Card center
) {}
