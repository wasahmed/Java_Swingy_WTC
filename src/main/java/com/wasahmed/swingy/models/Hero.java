package com.wasahmed.swingy.models;

import javax.validation.constraints.*;

public class Hero {
    @NotBlank(message = "Hero Name is a compulsory field")
    private String heroName;

    @NotNull(message = "Hero class can not be blank")
    private String heroClass;

    private int heroLevel;
    private int heroExp;
    private int heroAttack;
    private int defense;
    private int hp;
    private int xCoordinate;
    private int yCoordinate;

    public int getHeroAttack() {
        return heroAttack;
    }

    public void setHeroAttack(int heroAttack) {
        this.heroAttack = heroAttack;
    }

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public String getHeroClass() {
        return heroClass;
    }

    public void setHeroClass(String heroClass) {
        this.heroClass = heroClass;
    }

    public int getHeroLevel() {
        return heroLevel;
    }

    public void setHeroLevel(int heroLevel) {
        this.heroLevel = heroLevel;
    }

    public int getHeroExp() {
        return heroExp;
    }

    public void setHeroExp(int heroExp) {
        this.heroExp = heroExp;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }
}
