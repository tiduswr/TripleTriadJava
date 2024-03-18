package com.tiduswr.game.card;

public record Card(
    String name,
    int attackTop, 
    int attackBottom, 
    int attackLeft, 
    int attackRight,
    CardOwner cardOwner
) {}