package com.wild.game.gui;

import com.wild.game.entity.Ball;
import com.wild.game.entity.Paddle;
import com.wild.game.entity.Score;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Panel extends JPanel implements Runnable {
    public Panel() {
        newPaddle();
        newBall();
        score = new Score(WIDTH, HEIGHT);
        this.setFocusable(true);
        this.addKeyListener(new ActionListener());
        this.setPreferredSize(SCREEN_SIZE);
        thread = new Thread(this);
        thread.start();
    }

    public void newBall() {
        random = new Random();
        ball = new Ball((WIDTH / 2) - (BALL_DIAMETER / 2), random.nextInt(HEIGHT - BALL_DIAMETER), BALL_DIAMETER, BALL_DIAMETER);
    }

    public void newPaddle() {
        paddle1 = new Paddle(0, (HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 1);
        paddle2 = new Paddle(WIDTH - PADDLE_WIDTH, (HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 2);
    }

    public void paint(Graphics g) {
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);
    }

    public void draw(Graphics g) {
        paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);
        score.draw(g);
        Toolkit.getDefaultToolkit().sync();
    }

    public void move() {
        paddle1.move();
        paddle2.move();
        ball.move();
    }

    public void checkCollision() {
        if (paddle1.y <= 0) {
            paddle1.y = 0;
        }
        if (paddle1.y >= (HEIGHT - PADDLE_HEIGHT)) {
            paddle1.y = HEIGHT - PADDLE_HEIGHT;
        }
        if (paddle2.y <= 0) {
            paddle2.y = 0;
        }
        if (paddle2.y >= (HEIGHT - PADDLE_HEIGHT)) {
            paddle2.y = HEIGHT - PADDLE_HEIGHT;
        }
        if (ball.y <= 0) {
            ball.setYDirection(-ball.getYVelocity());
        }
        if (ball.y >= (HEIGHT - BALL_DIAMETER)) {
            ball.setYDirection(-ball.getYVelocity());
        }
        if (ball.intersects(paddle1)) {
            ball.setXVelocity(Math.abs(ball.getXVelocity()));
            ball.incrementXVelocity();
            if (ball.getYVelocity() > 0) {
                ball.incrementYVelocity();
            } else {
                ball.decreaseYVelocity();
            }
            ball.setXDirection(ball.getXVelocity());
            ball.setYDirection(ball.getYVelocity());
        }
        if (ball.intersects(paddle2)) {
            ball.setXVelocity(Math.abs(ball.getXVelocity()));
            ball.incrementXVelocity();
            if (ball.getYVelocity() > 0) {
                ball.incrementYVelocity();
            } else {
                ball.decreaseYVelocity();
            }
            ball.setXDirection(-ball.getXVelocity());
            ball.setYDirection(ball.getYVelocity());
        }
        if (ball.x <= 0) {
            score.incrementPlayer2Score();
            newPaddle();
            newBall();
        }
        if (ball.x >= (WIDTH - BALL_DIAMETER)) {
            score.incrementPlayer1Score();
            newPaddle();
            newBall();
        }
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountTicks = 60;
        double ns = 1000000000 / amountTicks;
        double delta = 0;
        while (true) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                move();
                checkCollision();
                repaint();
                delta--;
            }
        }
    }

    private class ActionListener extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);
        }

        public void keyReleased(KeyEvent e) {
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);
        }
    }

    private static final int WIDTH = 1000;
    private static final int HEIGHT = (int) (WIDTH * 0.5555);
    private static final Dimension SCREEN_SIZE = new Dimension(WIDTH, HEIGHT);
    private static final int BALL_DIAMETER = 20;
    private static final int PADDLE_WIDTH = 25;
    private static final int PADDLE_HEIGHT = 100;
    private Thread thread;
    private Image image;
    private Graphics graphics;
    private Random random;
    private Paddle paddle1;
    private Paddle paddle2;
    private Ball ball;
    private Score score;
}
