package com.wyc.tank;

/**
 * @Description
 * @Author wyc
 * @Date 2024/3/8
 */
public class T {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tankFrame = new TankFrame();
        while (true){
            Thread.sleep(100);
            tankFrame.repaint();
        }
    }
}
