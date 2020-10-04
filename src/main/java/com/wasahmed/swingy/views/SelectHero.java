package com.wasahmed.swingy.views;

import com.sun.org.apache.xml.internal.dtm.ref.sax2dtm.SAX2RTFDTM;
import com.wasahmed.swingy.controllers.GetData;
import com.wasahmed.swingy.controllers.Persist;
import com.wasahmed.swingy.controllers.World;
import com.wasahmed.swingy.models.Hero;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SelectHero implements ActionListener {

    private JLabel chooseHeroLabel;
    private JFrame frame;
    private JButton play;
    private JComboBox cb;
    String[] savedHero;
    ArrayList<Hero> heroes = new ArrayList<Hero>();
    Hero hero = new Hero();
    Persist p = new Persist();

    public SelectHero() {
        frame = new JFrame();
        chooseHeroLabel = new JLabel("SELECT HERO");
        play = new JButton("Play");
        heroes = p.readFile();
        savedHero = new String[1];
        for (int i = 0; i < savedHero.length; i++){
            String x = heroes.get(i).getHeroName();
            savedHero[i] = x;
        }
        cb = new JComboBox(savedHero);

        setUpGUI();

    }

    public void setUpGUI(){
        Container cp = frame.getContentPane();
        FlowLayout flow = new FlowLayout();
        cp.setLayout(flow);
        frame.setSize(100,100);
        frame.setTitle("Select Hero");
        cp.add(chooseHeroLabel);
        cp.add(cb);
        play.addActionListener(this);
        cp.add(play);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GetData heroInfo = new GetData();
        World world = new World();
        int xCo = 0;
        int yCo = 0;

        if (e.getSource() == play){
            for (int i = 0; i <= savedHero.length -1; i++){
                String chosen = cb.getSelectedItem().toString();
                if (chosen == savedHero[i]){
                    xCo = world.heroStartingPosition(heroes.get(i).getHeroLevel());
                    yCo = world.heroStartingPosition(heroes.get(i).getHeroLevel());
                    hero = heroInfo.createHero(heroes.get(i).getHeroName(), heroes.get(i).getHeroClass(),
                            heroes.get(i).getHeroLevel(), heroes.get(i).getHeroExp(), heroes.get(i).getHeroAttack(),
                            heroes.get(i).getDefense(), heroes.get(i).getHp(), xCo, yCo);
                    frame.dispose();
                    new Playground(hero);
                }
            }
        }
    }
}
