package com.wyc.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @Description
 * @Author wyc
 * @Date 2024/3/8
 */
public class TankFrame extends Frame {
    boolean bU = false;
    boolean bD = false;
    boolean bL = false;
    boolean bR = false;
    Tank myTank = new Tank(50,50,Dir.Down);

    Bullet bullet = new Bullet(300,300,Dir.Down);

    private Image offScreenImage;

    public TankFrame() {
        setTitle("demo01");
        setSize(1400, 600);
        setResizable(false);
        setVisible(true);
        // 添加窗口监听器以处理关闭事件
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println(e.getKeyChar());
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        System.out.println("上箭头键被按下");
                        bU = true;
                        break;
                    case KeyEvent.VK_DOWN:
                        System.out.println("下箭头键被按下");
                        bD = true;
                        break;
                    case KeyEvent.VK_LEFT:
                        System.out.println("左箭头键被按下");
                        bL = true;
                        break;
                    case KeyEvent.VK_RIGHT:
                        System.out.println("右箭头键被按下");
                        bR = true;
                        break;
                    default:
                        System.out.println("其他键被按下");
                        break;
                }
                setMainTankDir();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                System.out.println(e.getKeyChar());
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        System.out.println("上箭头键被抬起");
                        bU = false;
                        break;
                    case KeyEvent.VK_DOWN:
                        System.out.println("下箭头键被抬起");
                        bD = false;
                        break;
                    case KeyEvent.VK_LEFT:
                        System.out.println("左箭头键被抬起");
                        bL = false;
                        break;
                    case KeyEvent.VK_RIGHT:
                        System.out.println("右箭头键被抬起");
                        bR = false;
                        break;
                    default:
                        System.out.println("其他键被抬起");
                        break;
                }
                setMainTankDir();
            }

            private void setMainTankDir() {
                if (!bU && !bD && !bL && !bR) myTank.setMoving(false);
                else {
                    myTank.setMoving(true);
                    if (bU) myTank.setDir(Dir.Up);
                    if (bD) myTank.setDir(Dir.Down);
                    if (bL) myTank.setDir(Dir.Left);
                    if (bR) myTank.setDir(Dir.Right);
                }
                //repaint();
            }
        });
    }

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

    @Override
    public void paint(Graphics g) {
        myTank.paint(g);
        bullet.pain(g);
    }

    // 测试键盘监听


}
