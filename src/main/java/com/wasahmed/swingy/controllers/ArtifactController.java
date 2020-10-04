package com.wasahmed.swingy.controllers;

import com.wasahmed.swingy.models.Artifact;

import java.util.ArrayList;
import java.util.Random;

public class ArtifactController {
    ArrayList<Artifact> artifacts = new ArrayList<Artifact>();
    Artifact chosenOption = new Artifact();
    Artifact helmet = new Artifact();
    Artifact weapon = new Artifact();
    Artifact armour = new Artifact();

    public Artifact selection(){
        helmet.setArtifactName("helm");
        helmet.setIncreaseHP(125);
        weapon.setArtifactName("weapon");
        weapon.setIncreaseAttack(130);
        armour.setArtifactName("armour");
        armour.setIncreaseDefense(135);
        artifacts.add(helmet);
        artifacts.add(weapon);
        artifacts.add(armour);
        int heroOption = choice();
        if(heroOption == 1){
            chosenOption = randomize(artifacts);
            return chosenOption;
        }
        return (chosenOption);
    }

    public Artifact randomize(ArrayList<Artifact> artifacts){
        Random random = new Random();
        return artifacts.get(random.nextInt(artifacts.size()));
    }

    public int choice(){
        int[] heroSelection ={1,2};
        Random random = new Random();
        int j = random.nextInt(heroSelection.length);
        return (heroSelection[j]);
    }
}
