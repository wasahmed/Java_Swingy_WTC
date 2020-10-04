package com.wasahmed.swingy.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class Villain {
    private String villainName;
    private String villainClass;
    private int villainLevel;
    private int villainAttack;
    private int villainDefense;
    private int villainHP;
    private int x;
    private int y;

    public String getVillainName() {
        return villainName;
    }

    public void setVillainName(String villainName) {
        this.villainName = villainName;
    }

    public String getVillainClass() {
        return villainClass;
    }

    public void setVillainClass(String villainClass) {
        this.villainClass = villainClass;
    }

    public int getVillainLevel() {
        return villainLevel;
    }

    public void setVillainLevel(int villainLevel) {
        this.villainLevel = villainLevel;
    }

    public int getVillainAttack() {
        return villainAttack;
    }

    public void setVillainAttack(int villainAttack) {
        this.villainAttack = villainAttack;
    }

    public int getVillainDefense() {
        return villainDefense;
    }

    public void setVillainDefense(int villainDefense) {
        this.villainDefense = villainDefense;
    }

    public int getVillainHP() {
        return villainHP;
    }

    public void setVillainHP(int villainHP) {
        this.villainHP = villainHP;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
