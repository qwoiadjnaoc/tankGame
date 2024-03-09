package com.wyc.tank.MoveTest;

/**
 * @Description
 * @Author wyc
 * @Date 2024/3/8
 */
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MovingObjectFrame extends Frame {

    private Image offScreenImage;
    private int x = 100; // 物体的初始x坐标
    private int y = 100; // 物体的初始y坐标
    private final int STEP = 10; // 物体每次移动的步长

    public MovingObjectFrame() {
        // 初始化Frame
        setTitle("Moving Object Example");
        setSize(1200, 900);
        setVisible(true);
        System.out.println("Frame created");
        // 添加窗口监听器以处理关闭事件
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        // 添加键盘监听器
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:    y -= STEP; break; // 向上移动
                    case KeyEvent.VK_DOWN:  y += STEP; break; // 向下移动
                    case KeyEvent.VK_LEFT:  x -= STEP; break; // 向左移动
                    case KeyEvent.VK_RIGHT: x += STEP; break; // 向右移动
                }
                repaint(); // 重绘Frame以更新物体位置
            }
        });
    }

    // 重写update方法实现双缓冲
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(getWidth(), getHeight());
        }
        Graphics offG = offScreenImage.getGraphics();
        // 清除缓冲区
        offG.setColor(Color.WHITE);
        offG.fillRect(0, 0, getWidth(), getHeight());
        paint(offG); // 在缓冲区上绘制物体
        g.drawImage(offScreenImage, 0, 0, null); // 将缓冲区的图像一次性绘制到屏幕上
    }

    // 重写paint方法进行绘制
    public void paint(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, 50, 50); // 在新位置绘制物体
    }

    public static void main(String[] args) {
        new MovingObjectFrame();
    }
}

