package com.wasahmed.swingy.controllers;

import com.wasahmed.swingy.models.Artifact;
import com.wasahmed.swingy.models.Hero;

import java.io.*;
import java.util.ArrayList;

public class Persist {
    String heroName;
    String heroClass;
    int heroLevel = 0;
    int heroExp = 0;
    int heroAttack = 0;
    int heroDefense = 0;
    int heroHp = 0;

    public ArrayList<Hero> readFile(){
        ArrayList<Hero> heroes = new ArrayList<Hero>();
        String heroName;
        String heroClass;
        int heroLevel = 0;
        int heroExp = 0;
        int heroAttack = 0;
        int heroDefense = 0;
        int heroHp = 0;
        String l;

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("save.txt"));
            l = bufferedReader.readLine();

            while (l != null){
                l = l.trim();
                String[] heroStats = l.split(",");
                Hero hero = new Hero();
                heroName = heroStats[0];
                heroClass = heroStats[1];
                heroLevel = Integer.parseInt(heroStats[2]);
                heroExp = Integer.parseInt(heroStats[3]);
                heroAttack = Integer.parseInt(heroStats[4]);
                heroDefense = Integer.parseInt(heroStats[5]);
                heroHp = Integer.parseInt(heroStats[6]);

                hero.setHeroName(heroName);
                hero.setHeroClass(heroClass);
                hero.setHeroLevel(heroLevel);
                hero.setHeroExp(heroExp);
                hero.setHeroAttack(heroAttack);
                hero.setDefense(heroDefense);
                hero.setHp(heroHp);
                heroes.add(hero);

                l = bufferedReader.readLine();
            }
        }catch (IOException e){
            System.out.println("couldn't read");
        }
        return (heroes);

    }

    public void persist(Hero hero){
        this.heroName = hero.getHeroName();
        this.heroClass = hero.getHeroClass();
        this.heroLevel = hero.getHeroLevel();
        this.heroExp = hero.getHeroExp();
        this.heroAttack = hero.getHeroAttack();
        this.heroDefense = hero.getDefense();
        this.heroHp = hero.getHp();

        File file = new File("save.txt");
        try {
            System.out.println(heroName);
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write("");
            writer.append(
                    String.format("%s,%s,%s,%s,%s,%s,%s",
                            heroName, heroClass, heroLevel, heroExp, heroAttack, heroDefense, heroHp)
            );
            writer.close();
        }catch (IOException e){
            System.out.println("Failed to write to file");
        }
    }

}
