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

// 定义可移动物体类
class MovableObject2 {
    private int x, y; // 物体的坐标
    private int width, height; // 物体的尺寸
    private Color color; // 物体的颜色

    public MovableObject2(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    // 移动物体的方法
    public void move(int deltaX, int deltaY) {
        x += deltaX;
        y += deltaY;
    }

    // 绘制物体的方法
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }

    // 获取物体的位置和尺寸
    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
}

// 定义带有移动物体的Frame类
public class MovingObjectFrame2 extends Frame {

    private Image offScreenImage;
    private MovableObject2 movableObject; // 创建MovableObject实例

    public MovingObjectFrame2() {
        // 初始化Frame
        setTitle("Moving Object Example");
        setSize(400, 300);
        setResizable(false); // 禁止改变窗口大小
        setVisible(true);

        // 初始化可移动物体
        movableObject = new MovableObject2(100, 100, 50, 50, Color.RED);

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
                int step = 10; // 物体每次移动的步长
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:    movableObject.move(0, -step); break;
                    case KeyEvent.VK_DOWN:  movableObject.move(0, step); break;
                    case KeyEvent.VK_LEFT:  movableObject.move(-step, 0); break;
                    case KeyEvent.VK_RIGHT: movableObject.move(step, 0); break;
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
        movableObject.draw(g); // 使用MovableObject的draw方法绘制物体
    }

    public static void main(String[] args) {
        new MovingObjectFrame();
    }
}

