package com.wasahmed.swingy.controllers;

public class World {
    private int level;
    private int worldSize;
    private int[][] world;

    public int getWorldSize(int level){
        this.level =level;
        this.worldSize = (level -1) * 5 + 10 - (level%2);
        return(this.worldSize);
    }

    public boolean finishLine(int lvl, int mX, int mY, int hX, int hY){
        int worldSize = getWorldSize(lvl);

        if(hX <= 0 || hX >=mX)
            return true;
        if (hY <= 0 || hY >= mY)
            return true;
        return false;
    }

    public int heroStartingPosition(int level){
        int total = 0;
        int y = 0;
        int i = 0;
        int ave = getWorldSize(level);

        total = ave * (ave + 1)/2;
        total = total - ave;
        ave = total/ave;
        return (ave);
    }

    public int[][] consoleWorld(int level){
        this.level = level;
        this.worldSize = (level - 1) * 5 + 10 - (level%2);
        this.world = new int[this.worldSize][this.worldSize];
        return(this.world);
    }
}
