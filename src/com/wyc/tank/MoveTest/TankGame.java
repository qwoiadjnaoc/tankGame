package com.wyc.tank.MoveTest;

/**
 * @Description
 * @Author wyc
 * @Date 2024/3/9
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class TankGame extends JFrame {
    private GamePanel gamePanel = new GamePanel();

    public TankGame() {
        this.add(gamePanel);
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                gamePanel.keyPressed(e);
            }
        });
    }

    public static void main(String[] args) {
        TankGame tankGame = new TankGame();
        while (true){
            tankGame.repaint();
        }
    }
}

class GamePanel extends JPanel {
    PlayerTank playerTank = new PlayerTank(100, 100, 0);
    ArrayList<EnemyTank> enemyTanks = new ArrayList<>();
    ArrayList<Bullet> bullets = new ArrayList<>();

    public GamePanel() {
        enemyTanks.add(new EnemyTank(200, 200));
        enemyTanks.add(new EnemyTank(300, 300));
        Timer timer = new Timer(100, e -> {
            enemyTanks.forEach(EnemyTank::autoMove);
            repaint();
        });
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        playerTank.draw(g);
        for (EnemyTank enemyTank : enemyTanks) {
            enemyTank.draw(g);
        }
        for (Iterator<Bullet> iterator = bullets.iterator(); iterator.hasNext(); ) {
            Bullet bullet = iterator.next();
            if (bullet.move()) {
                iterator.remove();
            } else {
                bullet.draw(g);
            }
        }
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            playerTank.move(0, -5);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            playerTank.move(0, 5);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            playerTank.move(-5, 0);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            playerTank.move(5, 0);
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            bullets.add(playerTank.shoot());
        }
        checkCollisions();
        repaint();
    }

    public void checkCollisions() {
        for (Iterator<Bullet> bulletIterator = bullets.iterator(); bulletIterator.hasNext(); ) {
            Bullet bullet = bulletIterator.next();
            for (Iterator<EnemyTank> enemyIterator = enemyTanks.iterator(); enemyIterator.hasNext(); ) {
                EnemyTank enemyTank = enemyIterator.next();
                if (bullet.getRectangle().intersects(enemyTank.getRectangle())) {
                    enemyIterator.remove();
                    bulletIterator.remove();
                    break;
                }
            }
        }
    }
}

class Tank {
    int x, y, direction;
    static final int SIZE = 40;

    public Tank(int x, int y, int direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public Rectangle getRectangle() {
        return new Rectangle(x, y, SIZE, SIZE);
    }

    public void move(int dx, int dy) {
        x = Math.max(0, Math.min(x + dx, 800 - SIZE));
        y = Math.max(0, Math.min(y + dy, 600 - SIZE));
    }
}

class PlayerTank extends Tank {
    public PlayerTank(int x, int y, int direction) {
        super(x, y, direction);
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, SIZE, SIZE);
    }

    public Bullet shoot() {
        return new Bullet(x + SIZE / 2, y, direction);
    }
}

class EnemyTank extends Tank {
    Random random = new Random();

    public EnemyTank(int x, int y) {
        super(x, y, 0);
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, SIZE, SIZE);
    }

    public void autoMove() {
        int dx = random.nextInt(3) - 1; // -1, 0, or 1
        int dy = random.nextInt(3) - 1; // -1, 0, or 1
        move(dx * 5, dy * 5);
    }
}

class Bullet {
    int x, y, direction;
    static final int SPEED = 10;

    public Bullet(int x, int y, int direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public boolean move() {
        y -= SPEED;
        return y < 0;
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(x - 2, y - 10, 4, 10);
    }

    public Rectangle getRectangle() {
        return new Rectangle(x - 2, y - 10, 4, 10);
    }
}

