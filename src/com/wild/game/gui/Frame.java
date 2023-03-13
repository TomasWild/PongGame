package com.wild.game.gui;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    public Frame() {
        panel = new Panel();
        this.add(panel);
        this.setTitle("Pong: The Game");
        this.setIconImage(new ImageIcon("src/resources/icon.png").getImage());
        this.setBackground(Color.BLACK);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private Panel panel;
}
