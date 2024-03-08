package com.wyc.tank;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @Description
 * @Author wyc
 * @Date 2024/3/8
 */
public class TankFrame extends Frame {

    public TankFrame() {setSize(800, 600);
        setResizable(false);
        setTitle("demo01");
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }

        });
    }

    @Override
    public void paint(Graphics g) {
        System.out.println("绘制窗口");
        g.fillRect(200, 200, 50, 50);
    }
}
