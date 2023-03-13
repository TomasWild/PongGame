package com.wild.game.entity;

import java.awt.*;
import java.util.Random;

public class Ball extends Rectangle {
    public Ball() {
    }

    public Ball(int x, int y, int width, int height) {
        super(x, y, width, height);
        random = new Random();
        int randomXDirection = random.nextInt(2);
        if (randomXDirection == 0) {
            randomXDirection--;
        }
        setXDirection(randomXDirection * initialVelocity);
        int randomYDirection = random.nextInt(2);
        if (randomYDirection == 0) {
            randomYDirection--;
        }
        setYDirection(randomYDirection * initialVelocity);
    }

    public int getXVelocity() {
        return xVelocity;
    }

    public void setXVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }

    public int getYVelocity() {
        return yVelocity;
    }

    public void incrementXVelocity() {
        xVelocity++;
    }

    public void incrementYVelocity() {
        yVelocity++;
    }

    public void decreaseYVelocity() {
        yVelocity--;
    }

    public void setXDirection(int randomXDirection) {
        xVelocity = randomXDirection;
    }

    public void setYDirection(int randomYDirection) {
        yVelocity = randomYDirection;
    }

    public void move() {
        x += xVelocity;
        y += yVelocity;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, width, height);
    }

    private Random random;
    private int xVelocity;
    private int yVelocity;
    private int initialVelocity = 3;
}
