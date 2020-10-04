package com.wasahmed.swingy.controllers;

import com.wasahmed.swingy.models.Villain;

import java.util.ArrayList;
import java.util.Random;

public class CreateVillains {
    ArrayList<Villain> villains = new ArrayList<Villain>();
    private String[] villainClasses = {"Crawler","Zombie"};
    Random random = new Random();

    public ArrayList<Villain> spawn (int level){
        for(int i = 0; i <= level*25; i++){
            int j = random.nextInt(villainClasses.length);
            Villain villain = new Villain();
            villain.setVillainName(villainClasses[j]);
            villain.setVillainClass(villainClasses[j]);
            villain.setVillainLevel(random.nextInt(level +3 -(level + 1) + level));
            villain.setVillainAttack(random.nextInt(level*1500 -(level*900) + level*900));
            villain.setVillainDefense(random.nextInt(level*1500 -(level*900) + level*900));
            villain.setVillainHP(random.nextInt(level*1500 -(level*900) + level * 900));

            World world = new World();
            int worldSize = world.getWorldSize(level) -1;
            int hX = world.heroStartingPosition(level);
            int hY = world.heroStartingPosition(level);
            villain.setX(random.nextInt(worldSize + 1));
            villain.setY(random.nextInt(worldSize + 1));
            if(villain.getX() == hX){
                villain.setX(random.nextInt(worldSize + 1));
            }
            if (villain.getY() == hY){
                villain.setY(random.nextInt(worldSize + 1));
            }
            villains.add(villain);
        }
        return (villains);
    }
}
