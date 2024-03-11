package com.wyc.tank;

import java.awt.*;

/**
 * @Description
 * @Author wyc
 * @Date 2024/3/10
 */
public class Bullet {
    private static final int Speed = 2;
    private static int Width = 5,Height = 5;
    private int x, y;
    private Dir dir;

    public void pain(Graphics g){
        //Color color = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(x,y,Width,Height);
        //g.setColor(Color.RED);

        move();
    }

    public Bullet() {
    }

    private void move() {
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

    public Bullet(int x, int y, Dir dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }
}
