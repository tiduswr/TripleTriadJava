package com.tiduswr.game;

public record Card(
    String name,
    int attackTop, 
    int attackBottom, 
    int attackLeft, 
    int attackRight,
    CardOwner cardOwner
) {}