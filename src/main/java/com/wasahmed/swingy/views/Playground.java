package com.wasahmed.swingy.views;

import com.wasahmed.swingy.controllers.*;
import com.wasahmed.swingy.models.Artifact;
import com.wasahmed.swingy.models.Hero;
import com.wasahmed.swingy.models.Villain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Playground implements ActionListener {
    private JFrame frame;
    private JButton saveAndQuitButton, nButton, eButton, sButton, wButton;
    private JLabel mainLabel, heroNameLabel, heroClassLabel, heroLevelLabel, heroExpLabel, heroAttackLabel, heroDefenseLabel, heroHP, heroIdentifier, enemyIdentifier;
    private JPanel heroDetailsPanel, battleFieldPanel, navButtonsPanel;
    Hero hero = new Hero();
    World world;
    int maxXCoordinate = 0;
    int maxYCoordinate = 0;
    JPanel[][] blocks = new JPanel[1][1];
    ArrayList<Villain> villains = new ArrayList<Villain>();
    CreateVillains newVillain = new CreateVillains();
    Persist p = new Persist();
    boolean clash;
    Clash villainClashedWith = new Clash();
    ArtifactController artifactController = new ArtifactController();

    public Playground(Hero hero) {
        this.hero = hero;
        frame = new JFrame();
        mainLabel = new JLabel("BATTLEFIELD");
        mainLabel.setBackground(Color.cyan);
        heroNameLabel = new JLabel(hero.getHeroName());
        heroClassLabel = new JLabel(hero.getHeroClass());
        heroLevelLabel = new JLabel(String.valueOf(hero.getHeroLevel()));
        heroExpLabel = new JLabel(String.valueOf(hero.getHeroExp()));
        heroAttackLabel = new JLabel(String.valueOf(hero.getHeroAttack()));
        heroDefenseLabel = new JLabel(String.valueOf(hero.getDefense()));
        heroHP = new JLabel(String.valueOf(hero.getHp()));

        saveAndQuitButton = new JButton("Save & Quit");
        nButton = new JButton("NORTH");
        eButton = new JButton("EAST");
        sButton = new JButton("SOUTH");
        wButton = new JButton("WEST");

        world = new World();
        int worldSize = world.getWorldSize(hero.getHeroLevel());
        maxXCoordinate = worldSize -1;
        maxYCoordinate = worldSize -1;
        blocks = new JPanel[worldSize][worldSize];
        battleFieldPanel = new JPanel();
        battleFieldPanel.setBorder(BorderFactory.createLineBorder(Color.darkGray, 5));
        battleFieldPanel.setLayout(new GridLayout(worldSize, worldSize));

        for (int i = 0; i < worldSize; i++){
            for (int j = 0; j < worldSize; j++){
                blocks[i][j] = new JPanel();
                blocks[i][j].setBorder(BorderFactory.createLineBorder(Color.darkGray));
                battleFieldPanel.add(blocks[i][j]);
            }
        }
        setUpGui();
    }

    public void setUpGui(){
        heroDetailsPanel = new JPanel();
        heroDetailsPanel.setLayout(new GridLayout(1,0));
        heroDetailsPanel.add(mainLabel);
        heroDetailsPanel.add(heroNameLabel);
        heroDetailsPanel.add(heroClassLabel);
        heroDetailsPanel.add(heroLevelLabel);
        heroDetailsPanel.add(heroExpLabel);
        heroDetailsPanel.add(heroAttackLabel);
        heroDetailsPanel.add(heroDefenseLabel);
        heroDetailsPanel.add(heroHP);

        navButtonsPanel = new JPanel();
        navButtonsPanel.setLayout(new GridLayout(1,0));
        navButtonsPanel.add(nButton);
        navButtonsPanel.add(eButton);
        navButtonsPanel.add(sButton);
        navButtonsPanel.add(wButton);
        navButtonsPanel.add(saveAndQuitButton);

        nButton.addActionListener(this);
        eButton.addActionListener(this);
        sButton.addActionListener(this);
        wButton.addActionListener(this);
        saveAndQuitButton.addActionListener(this);

        villains = newVillain.spawn(hero.getHeroLevel());
        for (Villain villain: villains){
            enemyIdentifier = new JLabel("V");
            blocks[villain.getX()][villain.getY()].add(enemyIdentifier);
        }
        heroIdentifier = new JLabel("H");
        blocks[hero.getxCoordinate()][hero.getyCoordinate()].add(heroIdentifier);

        frame.add(heroDetailsPanel, BorderLayout.NORTH);
        frame.add(navButtonsPanel, BorderLayout.SOUTH);
        frame.add(battleFieldPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("BattleField");
        frame.pack();
        frame.setVisible(true);
    }

    public void victory(){
        JFrame victoryFrame;
        victoryFrame = new JFrame();
        int output = JOptionPane.showConfirmDialog(victoryFrame, "Victory is yours","Victory",JOptionPane.OK_CANCEL_OPTION);

        if (output == JOptionPane.OK_OPTION){
            victoryFrame.dispose();
            frame.dispose();
            new SelectHero();
        }
        if (output == JOptionPane.CANCEL_OPTION){
            System.exit(1);
        }
    }

    public boolean artifact(String a){
        JFrame keepArtfact;

        keepArtfact = new JFrame();
        int option = JOptionPane.showConfirmDialog(keepArtfact, "You found a "+ a + "Do you want to keep it?","Artifact", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION){
            return true;
        }
        else {
            keepArtfact.dispose();
            return false;
        }
    }

    public void defeat(){
        JFrame defeatFrame;
        defeatFrame = new JFrame();
        int option = JOptionPane.showConfirmDialog(defeatFrame, "YOU HAVE BEEN DEFEATED", "DEFEAT",JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION){
            defeatFrame.dispose();
            frame.dispose();
            new SelectHero();
        }
        if (option == JOptionPane.CANCEL_OPTION){
            System.exit(1);
        }
    }

    public void battleField(){
        JFrame dFrame;
        String outcome = "";
        Villain villainDefeated = new Villain();
        int vS =0;
        int level = 1;

        dFrame = new JFrame();
        int option = JOptionPane.showConfirmDialog(dFrame,"Do you want to Fight?","Fight",JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION){
            villainDefeated = villainClashedWith.getVillain(hero, villains);
            vS = villainClashedWith.xP(villainDefeated);
            outcome = villainClashedWith.fight(hero, villains);
            if (outcome == "victory"){
                hero.setHeroExp(hero.getHeroExp() + vS);
                level = villainClashedWith.levelUp(hero);
                hero.setHeroLevel(level);
                heroNameLabel.setText(hero.getHeroName());
                heroClassLabel.setText(hero.getHeroClass());
                heroLevelLabel.setText(String.valueOf(hero.getHeroLevel()));
                heroExpLabel.setText(String.valueOf(hero.getHeroExp()));
                heroAttackLabel.setText(String.valueOf(hero.getHeroAttack()));
                heroDefenseLabel.setText(String.valueOf(hero.getDefense()));
                heroHP.setText(String.valueOf(hero.getHp()));
                heroDetailsPanel.updateUI();

                Artifact selected = new Artifact();
                selected = artifactController.selection();

                if(selected.getArtifactName() == "helm"){
                    if (artifact("helm"))
                        hero.setHp(hero.getHp() + selected.getIncreaseHP());
                }
                if(selected.getArtifactName() == "weapon"){
                    if (artifact("weapon"))
                        hero.setHeroAttack(hero.getHeroAttack() + selected.getIncreaseAttack());
                }
                if(selected.getArtifactName() == "armour"){
                    if (artifact("armour"))
                        hero.setDefense(hero.getDefense() + selected.getIncreaseDefense());
                }
                heroHP.setText(String.valueOf(hero.getHp()));
                heroAttackLabel.setText(String.valueOf(hero.getHeroAttack()));
                heroDefenseLabel.setText(String.valueOf(hero.getDefense()));
                blocks[villainDefeated.getX()][villainDefeated.getY()].removeAll();
                villains = villainClashedWith.destroyVillain(hero, villains);
                blocks[hero.getxCoordinate()][hero.getyCoordinate()].add(heroIdentifier);
                blocks[villainDefeated.getX()][villainDefeated.getY()].updateUI();
            }
            if (outcome == "defeated")
                defeat();
        }
        if (option == JOptionPane.NO_OPTION)
            System.out.println("sissy");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int lvl = hero.getHeroLevel();

        if (e.getSource() == nButton){
            hero.setxCoordinate(hero.getxCoordinate() - 1);
            if (world.finishLine(lvl, maxXCoordinate, maxYCoordinate, hero.getxCoordinate(), hero.getyCoordinate())){
                blocks[hero.getxCoordinate()][hero.getyCoordinate()].add(heroIdentifier);
                battleFieldPanel.revalidate();
                battleFieldPanel.repaint();
                p.persist(hero);
                new SelectHero();
                victory();
            }
            blocks[hero.getxCoordinate()][hero.getyCoordinate()].add(heroIdentifier);
            battleFieldPanel.revalidate();
            battleFieldPanel.repaint();
            clash = villainClashedWith.checkIfClashing(hero, villains);
            if (clash)
                battleField();
        }
        if (e.getSource() == eButton){
            hero.setyCoordinate(hero.getyCoordinate() + 1);
            if (world.finishLine(lvl,maxXCoordinate, maxYCoordinate, hero.getxCoordinate(), hero.getyCoordinate())){
                blocks[hero.getxCoordinate()][hero.getyCoordinate()].add(heroIdentifier);
                battleFieldPanel.revalidate();
                battleFieldPanel.repaint();
                p.persist(hero);
                new SelectHero();
                victory();
            }
            blocks[hero.getxCoordinate()][hero.getyCoordinate()].add(heroIdentifier);
            battleFieldPanel.revalidate();
            battleFieldPanel.repaint();
            clash = villainClashedWith.checkIfClashing(hero, villains);
            if (clash)
                battleField();
        }
        if (e.getSource() == sButton) {
            hero.setxCoordinate(hero.getxCoordinate() + 1);
            if (world.finishLine(lvl, maxXCoordinate, maxYCoordinate, hero.getxCoordinate(), hero.getyCoordinate())) {
                blocks[hero.getxCoordinate()][hero.getyCoordinate()].add(heroIdentifier);
                battleFieldPanel.revalidate();
                battleFieldPanel.repaint();
                p.persist(hero);
                new SelectHero();
                victory();
            }
            blocks[hero.getxCoordinate()][hero.getyCoordinate()].add(heroIdentifier);
            battleFieldPanel.revalidate();
            battleFieldPanel.repaint();
            clash = villainClashedWith.checkIfClashing(hero, villains);
            if (clash)
                battleField();
        }
        if (e.getSource() == wButton) {
            hero.setyCoordinate(hero.getyCoordinate() - 1);
            if (world.finishLine(lvl, maxXCoordinate, maxYCoordinate, hero.getxCoordinate(), hero.getyCoordinate())) {
                blocks[hero.getxCoordinate()][hero.getyCoordinate()].add(heroIdentifier);
                battleFieldPanel.revalidate();
                battleFieldPanel.repaint();
                p.persist(hero);
                new SelectHero();
                victory();
            }
            blocks[hero.getxCoordinate()][hero.getyCoordinate()].add(heroIdentifier);
            battleFieldPanel.revalidate();
            battleFieldPanel.repaint();
            clash = villainClashedWith.checkIfClashing(hero, villains);
            if (clash)
                battleField();
        }
        if (e.getSource() == saveAndQuitButton){
            frame.dispose();
            p.persist(hero);
            new SelectHero();
        }
    }
}
