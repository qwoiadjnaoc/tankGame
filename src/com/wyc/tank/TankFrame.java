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

    int x = 200;
    int y = 200;
    boolean bU = false;
    boolean bD = false;
    boolean bL = false;
    boolean bR = false;

    public TankFrame() {
        setSize(800, 600);
        setResizable(false);
        setTitle("demo01");
        setVisible(true);
        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }

        });
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                System.out.println(e.getKeyChar());

            }

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
            }

            @Override
            public void keyReleased(KeyEvent e) {
                System.out.println(e.getKeyChar());
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        System.out.println("上箭头键被按下");
                        bU = false;
                        break;
                    case KeyEvent.VK_DOWN:
                        System.out.println("下箭头键被按下");
                        bD = false;
                        break;
                    case KeyEvent.VK_LEFT:
                        System.out.println("左箭头键被按下");
                        bL = false;
                        break;
                    case KeyEvent.VK_RIGHT:
                        System.out.println("右箭头键被按下");
                        bR = false;
                        break;
                    default:
                        System.out.println("其他键被按下");
                        break;
                }
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        System.out.println("绘制窗口");
        g.fillRect(x, y, 50, 50);
        g.setColor(Color.BLUE);
    }

    // 测试键盘监听


}
