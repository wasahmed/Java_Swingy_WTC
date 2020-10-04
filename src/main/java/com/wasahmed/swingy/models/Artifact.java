package com.wasahmed.swingy.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class Artifact {
    @NotBlank
    private String artifactName;

    @Positive
    private int increaseAttack;

    @Positive
    private int increaseDefense;

    @Positive
    private int increaseHP;

    public String getArtifactName() {
        return artifactName;
    }

    public void setArtifactName(String artifactName) {
        this.artifactName = artifactName;
    }

    public int getIncreaseAttack() {
        return increaseAttack;
    }

    public void setIncreaseAttack(int increaseAttack) {
        this.increaseAttack = increaseAttack;
    }

    public int getIncreaseDefense() {
        return increaseDefense;
    }

    public void setIncreaseDefense(int increaseDefense) {
        this.increaseDefense = increaseDefense;
    }

    public int getIncreaseHP() {
        return increaseHP;
    }

    public void setIncreaseHP(int increaseHP) {
        this.increaseHP = increaseHP;
    }
}
