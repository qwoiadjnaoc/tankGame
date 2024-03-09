package com.wyc.tank.MoveTest;

/**
 * @Description
 * @Author wyc
 * @Date 2024/3/8
 */

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class MovableObject3 {
    private int x, y;
    int newX;
    int newY;
    private int width = 50, height = 50;

    int windowWidth =  600;
    int windowHeight = 600;

    private Color color;

    public MovableObject3(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public void move(int deltaX, int deltaY) {int newX = x + deltaX;
        int newY = y + deltaY;

        // 检查新位置是否在窗口边界内
        if (newX >= 0 && newX + width <= windowWidth) {
            x = newX;
        }
        if (newY >= 0 + 25 && newY + height <= windowHeight) {
            y = newY;
        }
    }
    public void smartMove(int windowWidth, int windowHeight) {
        // 随机改变移动方向
        int deltaX = (int)(Math.random() * 5) - 2; // 在-2到2之间随机
        int deltaY = (int)(Math.random() * 5) - 2; // 在-2到2之间随机

        // 计算新位置
        int newX = x + deltaX;
        int newY = y + deltaY;

        // 检查新位置是否在窗口边界内
        if (newX >= 0 && newX + width <= windowWidth) {
            x = newX;
        } else {
            x -= deltaX; // 如果超出边界，反向移动
        }
        if (newY >= 0 && newY + height <= windowHeight) {
            y = newY;
        } else {
            y -= deltaY; // 如果超出边界，反向移动
        }
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }
}

public class MovingObjectFrame3 extends Frame {
    private Image offScreenImage;
    private MovableObject3 controlledObject; // 由键盘控制的物体
    private MovableObject3 autoMovingObject; // 自动移动的物体

    public MovingObjectFrame3() {
        setTitle("Moving Objects Example");
        setSize(600, 600);
        setResizable(false);
        setVisible(true);

        // 初始化被键盘控制的物体
        controlledObject = new MovableObject3(100, 100, 50, 50, Color.RED);
        // 初始化自动移动的物体
        autoMovingObject = new MovableObject3(150, 150, 50, 50, Color.BLUE);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        // 添加键盘监听器来控制物体
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int step = 10; // 物体每次移动的步长
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        controlledObject.move(0, -step);
                        System.out.println("Controlled object moved up");
                        break;
                    case KeyEvent.VK_DOWN:
                        controlledObject.move(0, step);
                        System.out.println("Controlled object moved down");
                        break;
                    case KeyEvent.VK_LEFT:
                        controlledObject.move(-step, 0);
                        System.out.println("Controlled object moved left");
                        break;
                    case KeyEvent.VK_RIGHT:
                        controlledObject.move(step, 0);
                        System.out.println("Controlled object moved right");
                        break;
                }
                repaint(); // 重绘Frame以更新物体位置
            }
        });

        // 创建一个线程来自动移动另一个物体
        Thread autoMoveThread = new Thread(() -> {
            while (true) {
                autoMovingObject.smartMove(10, 10); // 每次移动物体1个像素
                repaint();
                try {
                    Thread.sleep(20); // 暂停20毫秒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        autoMoveThread.start(); // 启动线程
    }

    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(getWidth(), getHeight());
        }
        Graphics offG = offScreenImage.getGraphics();
        offG.setColor(Color.WHITE);
        offG.fillRect(0, 0, getWidth(), getHeight());
        paint(offG);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    public void paint(Graphics g) {
        controlledObject.draw(g); // 绘制被键盘控制的物体
        autoMovingObject.draw(g); // 绘制自动移动的物体
    }

    public static void main(String[] args) {
        new MovingObjectFrame3();
        System.out.println("开始");
    }
}


