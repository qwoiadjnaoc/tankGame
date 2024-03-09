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
//            new Thread(() -> {
//                try {
//                    Thread.sleep(20);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//                tankFrame.repaint();
//            }).start();
            tankFrame.repaint();
        }
    }
}
