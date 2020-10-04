package com.wasahmed.swingy.views;

import com.wasahmed.swingy.controllers.*;
import com.wasahmed.swingy.models.Artifact;
import com.wasahmed.swingy.models.Hero;
import com.wasahmed.swingy.models.Villain;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Console {
    Hero hero = new Hero();
    ArrayList<Hero> heroes = new ArrayList<Hero>();
    Clash clash = new Clash();
    ArtifactController artifactController = new ArtifactController();
    World world = new World();
    GetData getData = new GetData();
    CreateVillains createVillains = new CreateVillains();
    ArrayList<Villain> villains = new ArrayList<Villain>();
    Scanner scanner;
    Persist p = new Persist();
    int[][] field;
    int userSelection = 0;
    String[] savedHeroes;

    public void runGameInConsole() {
        Villain defeatedVillain = new Villain();
        String outcome = null;
        String heroName = null;
        String heroClass = "";
        int heroLevel = 0;
        int heroExp = 0;
        int heroAttack = 0;
        int heroDefense = 0;
        int heroHp = 0;
        int xCoOrdinate;
        int yCoOrdinate;
        int maxXValue;
        int maxYValue;
        int villainStats = 0;
        int lvl = 1;

        scanner = new Scanner(System.in);
        System.out.println("WELCOME TO SWINGY!!!\n" + "\t1. Create Hero\n\t2. Select Hero");
        getUserSelection();
//        System.out.println(userSelection);
        if (userSelection == 1) {
            System.out.println("\tHero Name: ");
            scanner.nextLine();
            heroName = scanner.nextLine();
//            System.out.println(heroName);
            if (heroName.isEmpty())
                runGameInConsole();

            System.out.println("\tHero Class:\n\t1. Super Saiyan\n");
            getUserSelection();
//            System.out.println(userSelection);
            if (userSelection == 1) {
                heroClass = "Super saiyan";
                heroLevel = 1;
                heroExp = 1000;
                heroAttack = 1000;
                heroDefense = 1000;
                heroHp = 1000;
            } else {
                System.out.println("Not a valid option");
                runGameInConsole();
            }
            System.out.println(String.format("Hero Created--> Name: %s, Class: %s, Level: %s, Exp: %s, Attack: %s, Defense: %s, Hp: %s",
                    heroName, heroClass, heroLevel, heroExp, heroAttack, heroDefense, heroHp));
            xCoOrdinate = world.heroStartingPosition(heroLevel);
            yCoOrdinate = world.heroStartingPosition(heroLevel);
            hero = getData.createHero(heroName, heroClass, heroLevel, heroExp, heroAttack, heroDefense, heroHp, xCoOrdinate, yCoOrdinate);

            field = world.consoleWorld(hero.getHeroLevel());
            villains = createVillains.spawn(hero.getHeroLevel());
            field[hero.getxCoordinate()][hero.getyCoordinate()] = 1;
            for (Villain villain : villains) {
                field[villain.getX()][villain.getY()] = 2;
            }
            int meet = 1;
            for (int i = 0; i < world.getWorldSize(heroLevel); i++) {
                for (int j = 0; j < world.getWorldSize(heroLevel); j++) {
                    System.out.printf("%2d", field[i][j]);
                }
                System.out.println();
            }

            while (meet != 0) {
                outcome = clash.fight(hero, villains);
                field[hero.getxCoordinate()][hero.getyCoordinate()] = 0;
                traverse(hero);
                field[hero.getxCoordinate()][hero.getyCoordinate()] = 1;
                for (int i = 0; i < world.getWorldSize(heroLevel); i++) {
                    for (int j = 0; j < world.getWorldSize(heroLevel); j++) {
                        System.out.printf("%2d", field[i][j]);
                    }
                    System.out.println();
                }

                int worldSize = world.getWorldSize(hero.getHeroLevel());
                maxXValue = worldSize - 1;
                maxYValue = worldSize - 1;
                if (world.finishLine(heroLevel, maxXValue, maxYValue, hero.getxCoordinate(), hero.getyCoordinate())) {
                    System.out.println("Victory is yours");
                    System.out.println(String.format("Name: %s, Class: %s, Level: %s, Exp: %s, Attack: %s, Defense: %s, Hp: %s",
                            hero.getHeroName(),hero.getHeroClass(),hero.getHeroLevel(),hero.getHeroExp(),hero.getHeroAttack(),hero.getDefense(),hero.getHp()));
                    p.persist(hero);
                    System.exit(1);
                }
                if (clash.checkIfClashing(hero, villains)) {
                    defeatedVillain = clash.getVillain(hero, villains);
                    villainStats = clash.xP(defeatedVillain);
                    System.out.println("FIGHT?\n\t1.YES\n\t2.NO");
                    getUserSelection();
                    if (userSelection == 1) {
                        outcome = clash.fight(hero, villains);
                        if (outcome == "victory") {
                            System.out.println("\tBattle Won");
                            villains = clash.destroyVillain(hero, villains);
                            hero.setHeroExp(hero.getHeroExp() + villainStats);
                            lvl = clash.levelUp(hero);
                            hero.setHeroLevel(lvl);
                            Artifact getArtifact = new Artifact();
                            getArtifact = artifactController.selection();
                            System.out.println("Artifact collected is " + getArtifact.getArtifactName());
                            if (getArtifact.getArtifactName() == "helm") {
                                System.out.println("You found a " + getArtifact.getArtifactName() + "\n\t1.Pick up\n\t2.Drop");
                                getUserSelection();
                                if (userSelection == 1)
                                    hero.setHp(hero.getHp() + getArtifact.getIncreaseHP());
                                else if (userSelection == 2)
                                    System.out.println("Dropped");
                                System.out.println(String.format("Name: %s, Class: %s, Level: %s, Exp: %s, Attack: %s, Defense: %s, Hp: %s",
                                        hero.getHeroName(),hero.getHeroClass(),hero.getHeroLevel(),hero.getHeroExp(),hero.getHeroAttack(),hero.getDefense(),hero.getHp()));
                            } else if (getArtifact.getArtifactName() == "weapon") {
                                System.out.println("You found a " + getArtifact.getArtifactName() + "\n\t1.Pick up\n\t2.Drop");
                                getUserSelection();
                                if (userSelection == 1) {
                                    hero.setHeroAttack(hero.getHeroAttack() + getArtifact.getIncreaseAttack());
                                    System.out.println("You have recieved a weapon artifact your Attack has increased by " + getArtifact.getIncreaseAttack());
                                } else if (userSelection == 2) {
                                    System.out.println("Dropped");
                                }
                                System.out.println(String.format("Name: %s, Class: %s, Level: %s, Exp: %s, Attack: %s, Defense: %s, Hp: %s",
                                        hero.getHeroName(),hero.getHeroClass(),hero.getHeroLevel(),hero.getHeroExp(),hero.getHeroAttack(),hero.getDefense(),hero.getHp()));
                            } else if (getArtifact.getArtifactName() == "armour") {
                                System.out.println("You found a " + getArtifact.getArtifactName() + "\n\t1.Pick up\n\t2.Drop");
                                getUserSelection();
                                if (userSelection == 1) {
                                    hero.setHeroAttack(hero.getDefense() + getArtifact.getIncreaseDefense());
                                    System.out.println("You have recieved a weapon artifact your Attack has increased by " + getArtifact.getIncreaseDefense());
                                } else if (userSelection == 2) {
                                    System.out.println("Dropped");
                                }
                                System.out.println(String.format("Name: %s, Class: %s, Level: %s, Exp: %s, Attack: %s, Defense: %s, Hp: %s",
                                        hero.getHeroName(),hero.getHeroClass(),hero.getHeroLevel(),hero.getHeroExp(),hero.getHeroAttack(),hero.getDefense(),hero.getHp()));
                            }
                        } else {
                            System.out.println("You have been defeated");
                            System.exit(1);
                        }
                    } else {
                        field[hero.getxCoordinate()][hero.getyCoordinate()] = 2;
                        continue;
                    }

                }
            }
        }else if (userSelection == 2){
            System.out.println("read from txt");
            heroes = p.readFile();
            savedHeroes = new String[heroes.size()];
            for(int i = 0; i < savedHeroes.length; i++){
                String hName = "1. "+ heroes.get(i).getHeroName();
                savedHeroes[i] =hName;
                System.out.println(savedHeroes[i]);
            }
            System.out.println("Select hero");
            getUserSelection();
            if (userSelection > savedHeroes.length || userSelection != 1){
                System.out.println("Invalid");
                runGameInConsole();
            }
            for (int i = 1; i <= savedHeroes.length; i++){
                if (userSelection == i){
                    heroName = heroes.get(i-1).getHeroName();
                    heroClass = heroes.get(i-1).getHeroClass();
                    heroLevel = heroes.get(i-1).getHeroLevel();
                    heroExp = heroes.get(i-1).getHeroExp();
                    heroAttack = heroes.get(i-1).getHeroAttack();
                    heroDefense = heroes.get(i-1).getDefense();
                    heroHp = heroes.get(i-1).getHp();
                }
            }
            xCoOrdinate = world.heroStartingPosition(heroLevel);
            yCoOrdinate = world.heroStartingPosition(heroLevel);
            hero = getData.createHero(heroName,heroClass,heroLevel,heroExp,heroAttack,heroAttack,heroHp,xCoOrdinate,yCoOrdinate);
            villains = createVillains.spawn(hero.getHeroLevel());
            field = world.consoleWorld(heroLevel);
            field[hero.getxCoordinate()][hero.getyCoordinate()] = 1;
            for (Villain villain: villains){
                field[villain.getX()][villain.getY()] = 2;
            }

            int meet = 1;
            for (int i = 0; i < world.getWorldSize(heroLevel); i++) {
                for (int j = 0; j < world.getWorldSize(heroLevel); j++) {
                    System.out.printf("%2d", field[i][j]);
                }
                System.out.println();
            }
            System.out.println(String.format("Hero Created--> Name: %s, Class: %s, Level: %s, Exp: %s, Attack: %s, Defense: %s, Hp: %s",
                    hero.getHeroName(),hero.getHeroClass(),hero.getHeroLevel(),hero.getHeroExp(),hero.getHeroAttack(),hero.getDefense(),hero.getHp()));
            while (meet != 0) {
                outcome = clash.fight(hero, villains);
                field[hero.getxCoordinate()][hero.getyCoordinate()] = 0;
                traverse(hero);
                field[hero.getxCoordinate()][hero.getyCoordinate()] = 1;
                for (int i = 0; i < world.getWorldSize(heroLevel); i++) {
                    for (int j = 0; j < world.getWorldSize(heroLevel); j++) {
                        System.out.printf("%2d", field[i][j]);
                    }
                    System.out.println();
                }

                int worldSize = world.getWorldSize(hero.getHeroLevel());
                maxXValue = worldSize - 1;
                maxYValue = worldSize - 1;
                if (world.finishLine(heroLevel, maxXValue, maxYValue, hero.getxCoordinate(), hero.getyCoordinate())) {
                    System.out.println("Victory is yours");
                    System.out.println(String.format("Name: %s, Class: %s, Level: %s, Exp: %s, Attack: %s, Defense: %s, Hp: %s",
                            hero.getHeroName(),hero.getHeroClass(),hero.getHeroLevel(),hero.getHeroExp(),hero.getHeroAttack(),hero.getDefense(),hero.getHp()));
                    p.persist(hero);
                    System.exit(1);
                }
                if (clash.checkIfClashing(hero, villains)) {
                    defeatedVillain = clash.getVillain(hero, villains);
                    villainStats = clash.xP(defeatedVillain);
                    System.out.println("FIGHT?\n\t1.YES\n\t2.NO");
                    getUserSelection();
                    if (userSelection == 1) {
                        outcome = clash.fight(hero, villains);
                        if (outcome == "victory") {
                            System.out.println("\tBattle Won");
                            villains = clash.destroyVillain(hero, villains);
                            hero.setHeroExp(hero.getHeroExp() + villainStats);
                            lvl = clash.levelUp(hero);
                            hero.setHeroLevel(lvl);
                            Artifact getArtifact = new Artifact();
                            getArtifact = artifactController.selection();
                            System.out.println("Artifact collected is " + getArtifact.getArtifactName());
                            if (getArtifact.getArtifactName() == "helm") {
                                System.out.println("You found a " + getArtifact.getArtifactName() + "\n\t1.Pick up\n\t2.Drop");
                                getUserSelection();
                                if (userSelection == 1)
                                    hero.setHp(hero.getHp() + getArtifact.getIncreaseHP());
                                else if (userSelection == 2)
                                    System.out.println("Dropped");
                                System.out.println(String.format("Name: %s, Class: %s, Level: %s, Exp: %s, Attack: %s, Defense: %s, Hp: %s",
                                        hero.getHeroName(),hero.getHeroClass(),hero.getHeroLevel(),hero.getHeroExp(),hero.getHeroAttack(),hero.getDefense(),hero.getHp()));
                            } else if (getArtifact.getArtifactName() == "weapon") {
                                System.out.println("You found a " + getArtifact.getArtifactName() + "\n\t1.Pick up\n\t2.Drop");
                                getUserSelection();
                                if (userSelection == 1) {
                                    hero.setHeroAttack(hero.getHeroAttack() + getArtifact.getIncreaseAttack());
                                    System.out.println("You have recieved a weapon artifact your Attack has increased by " + getArtifact.getIncreaseAttack());
                                } else if (userSelection == 2) {
                                    System.out.println("Dropped");
                                }
                                System.out.println(String.format("Name: %s, Class: %s, Level: %s, Exp: %s, Attack: %s, Defense: %s, Hp: %s",
                                        hero.getHeroName(),hero.getHeroClass(),hero.getHeroLevel(),hero.getHeroExp(),hero.getHeroAttack(),hero.getDefense(),hero.getHp()));
                            } else if (getArtifact.getArtifactName() == "armour") {
                                System.out.println("You found a " + getArtifact.getArtifactName() + "\n\t1.Pick up\n\t2.Drop");
                                getUserSelection();
                                if (userSelection == 1) {
                                    hero.setHeroAttack(hero.getDefense() + getArtifact.getIncreaseDefense());
                                    System.out.println("You have recieved a weapon artifact your Attack has increased by " + getArtifact.getIncreaseDefense());
                                } else if (userSelection == 2) {
                                    System.out.println("Dropped");
                                }
                                System.out.println(String.format("Name: %s, Class: %s, Level: %s, Exp: %s, Attack: %s, Defense: %s, Hp: %s",
                                        hero.getHeroName(),hero.getHeroClass(),hero.getHeroLevel(),hero.getHeroExp(),hero.getHeroAttack(),hero.getDefense(),hero.getHp()));
                            }
                        } else {
                            System.out.println("You have been defeated");
                            System.exit(1);
                        }
                    } else {
                        field[hero.getxCoordinate()][hero.getyCoordinate()] = 2;
                        continue;
                    }

                }
            }
        }else {
            System.out.println("Invalid");
            runGameInConsole();
        }
    }

    public void getUserSelection(){
        try{
            userSelection = scanner.nextInt();
        }catch (InputMismatchException e){
            System.out.println(e.getMessage());
            runGameInConsole();
        }
    }

    public void traverse(Hero hero){
        scanner = new Scanner(System.in);
//        System.out.println(String.format("Hero Created--> Name: %s, Class: %s, Level: %s, Exp: %s, Attack: %s, Defense: %s, Hp: %s",
//                hero.getHeroName(),hero.getHeroClass(),hero.getHeroLevel(),hero.getHeroExp(),hero.getHeroAttack(),hero.getDefense(),hero.getHp()));
        System.out.println("Navigate your hero: \n\t 1.N\t2.E\t3.S\t4.W\t5.E");
        try {
            userSelection = scanner.nextInt();
        }catch (InputMismatchException e){
            System.out.println(e.getMessage());
            userSelection = 0;
        }
        if(userSelection == 1)
            hero.setxCoordinate(hero.getxCoordinate() -1);
        else if (userSelection == 2)
            hero.setyCoordinate(hero.getyCoordinate() + 1);
        else if (userSelection == 3)
            hero.setxCoordinate(hero.getxCoordinate() + 1);
        else if (userSelection == 4)
            hero.setyCoordinate(hero.getyCoordinate() -1);
        else if (userSelection == 5){
            p.persist(hero);
            System.exit(1);
        }else
            System.out.println("Invalid");
    }
}
