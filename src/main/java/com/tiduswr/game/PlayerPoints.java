package com.tiduswr.game;

public class PlayerPoints {

    private int points;

    public PlayerPoints(int initialPoints){
        points = initialPoints;
    }

    public void addPoint(){
        points++;
    }

    public void removePoint(){
        if(points > 0) points--;
    }

    public int checkPoints(){
        return points;
    }

}
