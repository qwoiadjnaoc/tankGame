package com.wyc.tank;

import java.awt.*;

/**
 * @Description
 * @Author wyc
 * @Date 2024/3/8
 */
public class Tank {

    private int x, y;
    private Dir dir = Dir.Down;
    private static final int Speed = 1;

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    private boolean moving = false;


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }


    public Tank() {
    }

    public Tank(int x, int y, Dir dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }


    public void paint(Graphics g) {
//        new Thread(() -> {
//            try {
//                Thread.sleep(20);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            move();
//        }).start();
        move();
        g.setColor(Color.BLUE);
        g.fillRect(x, y, 50, 50);
        System.out.println("绘图");
    }

    private void move() {
        if (!moving) return;
        switch (dir) {
            case Up:
                y -= Speed;
                break;
            case Down:
                y += Speed;
                break;
            case Left:
                x -= Speed;
                break;
            case Right:
                x += Speed;
                break;
            default:
                dir = Dir.Stop;
                break;
        }
    }
}
