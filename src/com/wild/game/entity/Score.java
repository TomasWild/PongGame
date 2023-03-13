package com.wild.game.entity;

import java.awt.*;

public class Score extends Rectangle {
    public Score() {
    }

    public Score(int width, int height) {
        Score.WIDTH = width;
        Score.HEIGHT = height;
    }

    public void incrementPlayer1Score() {
        scorePlayer1++;
    }

    public void incrementPlayer2Score() {
        scorePlayer2++;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Free Ink", Font.BOLD, 60));
        g.drawLine(WIDTH / 2, 0, WIDTH / 2, HEIGHT);
        g.drawString(String.valueOf(scorePlayer1 / 10) + String.valueOf(scorePlayer1 % 10), (WIDTH / 2) - 85, 50);
        g.drawString(String.valueOf(scorePlayer2 / 10) + String.valueOf(scorePlayer2 % 10), (WIDTH / 2) + 20, 50);
    }

    private static int WIDTH;
    private static int HEIGHT;
    private int scorePlayer1;
    private int scorePlayer2;
}
