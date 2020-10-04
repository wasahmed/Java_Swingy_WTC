package com.wasahmed.swingy.views;

import com.wasahmed.swingy.controllers.GetData;
import com.wasahmed.swingy.controllers.World;
import com.wasahmed.swingy.models.Hero;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateHero implements ActionListener {
    private JFrame frame;
    private JLabel headerLabel, heroNameLabel, heroClassLabel;
    private JTextField heroNameField, heroClassField;
    private JButton createHeroButton;

    public CreateHero() {
        frame = new JFrame();
        headerLabel = new JLabel("Create Hero");
        heroNameLabel = new JLabel("Hero Name");
        heroClassLabel = new JLabel("Hero class (SS1|SS2|SS3)");
        heroNameField = new JTextField(15);
        heroClassField= new JTextField(15);
        createHeroButton = new JButton("Create Hero");
        setUpGui();
    }

    public void setUpGui(){
        Container cp = frame.getContentPane();
        FlowLayout flow = new FlowLayout();
        cp.setLayout(flow);
        frame.setSize(100,170);
        frame.setTitle("Create Hero");
        cp.add(headerLabel);
        cp.add(heroNameLabel);
        cp.add(heroNameField);
        cp.add(heroClassLabel);
        cp.add(heroClassField);
        cp.add(createHeroButton);

        createHeroButton.addActionListener(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object ae = e.getSource();
        if(ae.equals(this.createHeroButton)){
            World world = new World();
            String heroClass = heroClassField.getText();
            String heroName = heroNameField.getText();
            int heroLevel = 1;
            int heroExp = 1000;
            int heroAttack = 1000;
            int heroDefense = 1000;
            int heroHitPoints = 1000;
            int xCoordinate = world.heroStartingPosition(heroLevel);
            int yCoordinate = world.heroStartingPosition(heroLevel);

            GetData getData = new GetData();
            Hero hero = new Hero();
            hero = getData.createHero(heroName, heroClass, heroLevel, heroExp, heroAttack, heroDefense, heroHitPoints, xCoordinate, yCoordinate);
            new Playground(hero);
            frame.dispose();
        }
    }
}
