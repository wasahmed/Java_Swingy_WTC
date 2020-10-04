package com.wasahmed.swingy.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FirstScreen implements ActionListener {

    private JFrame frame;
    private JLabel label;
    private JButton createHeroButton;
    private JButton selectHeroButton;
    private int width;
    private int height;

    public FirstScreen(int width, int height) {
        frame = new JFrame();
        label = new JLabel("WELCOME TO SWINGY");
        createHeroButton = new JButton("Create Hero");
        selectHeroButton = new JButton("Select Hero");
        this.width = width;
        this.height = height;

        setUpGui();
    }

    public void setUpGui(){
        Container cp = frame.getContentPane();
        FlowLayout flow = new FlowLayout();
        cp.setLayout(flow);
        frame.setSize(width,height);
        frame.setTitle("Swingy");
        cp.add(label);
        cp.add(this.createHeroButton);
        cp.add(selectHeroButton);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createHeroButton.addActionListener(this);
        selectHeroButton.addActionListener(this);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object ae = e.getSource();
        if(ae.equals(this.createHeroButton)){
            new CreateHero();
            frame.dispose();
        }
        if(ae.equals(this.selectHeroButton)){
            new SelectHero();
            frame.dispose();
        }
    }
}
