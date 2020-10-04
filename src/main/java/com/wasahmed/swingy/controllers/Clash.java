package com.wasahmed.swingy.controllers;

import com.wasahmed.swingy.models.Hero;
import com.wasahmed.swingy.models.Villain;

import java.util.ArrayList;
import java.util.Iterator;

public class Clash {
    public boolean checkIfClashing(Hero hero, ArrayList<Villain> villains){
        int hX = hero.getxCoordinate();
        int hY = hero.getyCoordinate();

        for (Villain villain: villains){
            if (villain.getX()==hX && villain.getY()==hY)
                return true;
        }
        return false;
    }

    public String fight(Hero h, ArrayList<Villain> villains){
        int hX = h.getxCoordinate();
        int hY = h.getyCoordinate();
        Hero hero = new Hero();
        hero = h;

        for (Villain villain: villains){
            if (villain.getX() == hX && villain.getY() == hY){
                while (hero.getHp() > 0 && villain.getVillainHP() > 0){
                    if(hero.getHeroAttack() > villain.getVillainDefense())
                        villain.setVillainHP((int)(villain.getVillainHP() - (villain.getVillainHP()*0.2)));
                    else if(villain.getVillainAttack() > hero.getDefense())
                        hero.setHp((int) (hero.getHp() -(hero.getHp() *0.2)));
                    else if(hero.getDefense() > villain.getVillainAttack())
                        villain.setVillainHP((int) (villain.getVillainHP() - (villain.getVillainHP()*0.2)));
                    else if(villain.getVillainDefense() > hero.getHeroAttack())
                        hero.setHp((int) (hero.getHp() - (hero.getHp()*0.2)));
                    if(hero.getHp() <= 0)
                        return ("defeated");
                    else if(villain.getVillainHP() <= 0)
                        return "victory";
                }
            }
        }
        return ("err");
    }

    public Villain getVillain(Hero hero, ArrayList<Villain> villains){
        int hX = hero.getxCoordinate();
        int hY = hero.getyCoordinate();
        Villain vil = new Villain();

        for (Villain v: villains){
            if (v.getX() == hX && v.getY() == hY)
                return v;
        }
        return vil;
    }

    public int xP(Villain villain){
        int incXp = villain.getVillainHP() + villain.getVillainAttack() + villain.getVillainDefense();
        System.out.println(incXp);
        incXp /=3;
        incXp /=2;
        return (incXp);
    }

    public ArrayList<Villain> destroyVillain(Hero hero, ArrayList<Villain> villains){
        int hX = hero.getxCoordinate();
        int hY = hero.getyCoordinate();

        ArrayList<Villain> latestVillainSnapShot = new ArrayList<Villain>();
        Villain villain = new Villain();

        latestVillainSnapShot = villains;
        Iterator<Villain> i = latestVillainSnapShot.iterator();
        while (i.hasNext()){
            villain = i.next();
            if (villain.getX()== hX && villain.getY() == hY)
                i.remove();
        }
        return latestVillainSnapShot;
    }

    public int levelUp(Hero hero) {
        int xp = hero.getHeroExp();
        int lvl1 = 1000;
        int lvl2 = 2450;
        int lvl3 = 4800;
        int lvl4 = 8050;
        int lvl5 = 12200;
        int lvl6 = 17250;
        int lvl7 = 23200;
        int lvl8 = 30050;
        int lvl9 = 37800;
        int lvl10 = 46450;

        if(xp >=0 && xp < lvl2){
            hero.setHeroLevel(1);
            return(1);
        }else if(xp > lvl1 && xp < lvl3){
            hero.setHeroLevel(2);
            return(2);
        }
        else if(xp > lvl2 && xp < lvl4){
            hero.setHeroLevel(3);
            return(3);
        }else if(xp > lvl3 && xp < lvl5){
            hero.setHeroLevel(4);
            return(4);
        }else if(xp > lvl4 && xp < lvl6){
            hero.setHeroLevel(5);
            return(5);
        }else if(xp > lvl5 && xp < lvl7){
            hero.setHeroLevel(6);
            return(6);
        }else if(xp > lvl6 && xp < lvl8){
            hero.setHeroLevel(7);
            return(7);
        }else if(xp > lvl7 && xp < lvl9){
            hero.setHeroLevel(8);
            return(8);
        }else if(xp > lvl8 && xp < lvl10){
            hero.setHeroLevel(9);
            return(9);
        }else if(xp > lvl9){
            hero.setHeroLevel(10);
            return(10);
        }

        return (1);

    }
}
